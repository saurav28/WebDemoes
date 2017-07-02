package org.saurav.casestudy.cf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNameConstraintViolationException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

import com.sap.ecm.api.EcmService;
import com.sap.ecm.api.RepositoryOptions;
import com.sap.ecm.api.RepositoryOptions.Visibility;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.getWriter().println("<html><body>");
		    try {
		      // Use a unique name with package semantics e.g. com.foo.MyRepository
		      String uniqueName = "com.foo.MyRepository";
		      // Use a secret key only known to your application (min. 10 chars)
		      String secretKey = "my_super_secret_key_123";
		      Session openCmisSession = null;
		        InitialContext ctx = new InitialContext();
		        String lookupName = "java:comp/env/" + "EcmService";
		        EcmService ecmSvc = (EcmService) ctx.lookup(lookupName);
		      try {
		        // connect to my repository
		        openCmisSession = ecmSvc.connect(uniqueName, secretKey);
		      }
		      catch (CmisObjectNotFoundException e) {
		        // repository does not exist, so try to create it
		        RepositoryOptions options = new RepositoryOptions();
		        options.setUniqueName(uniqueName);
		        options.setRepositoryKey(secretKey);
		        options.setVisibility(Visibility.PROTECTED);
		        ecmSvc.createRepository(options);
		        // should be created now, so connect to it
		        openCmisSession = ecmSvc.connect(uniqueName, secretKey);
		      }
		      response.getWriter().println(
		        "<h3>You are now connected to the Repository with Id "
		        + openCmisSession.getRepositoryInfo().getId()
		        + "</h3>");

		      // access the root folder of the repository
		      Folder root = openCmisSession.getRootFolder();

		      // create a new folder
		      Map<String, String> newFolderProps = new HashMap<String, String>();
		      newFolderProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
		      newFolderProps.put(PropertyIds.NAME, "SapHANANeo");
		      try {
		        root.createFolder(newFolderProps);
		      } catch (CmisNameConstraintViolationException e) {
		        // Folder exists already, nothing to do
		      }

		      // create a new file in the root folder
		      Map<String, Object> properties = new HashMap<String, Object>();
		      properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		      properties.put(PropertyIds.NAME, "HelloWorld.txt");
		      byte[] helloContent = "Hello World!".getBytes("UTF-8");
		      InputStream stream = new ByteArrayInputStream(helloContent);
		      ContentStream contentStream = openCmisSession.getObjectFactory()
		                                    .createContentStream("HelloWorld.txt",
		                                    helloContent.length, "text/plain; charset=UTF-8", stream);
		      try {
		        root.createDocument(properties, contentStream, VersioningState.NONE);
		      } catch (CmisNameConstraintViolationException e) {
		        // Document exists already, nothing to do
		      }

		      // Display the root folder's children objects
		      ItemIterable<CmisObject> children = root.getChildren();
		      response.getWriter().println("The root folder of the repository with id " + root.getId()
		                                 + " contains the following objects:<ul>");
		      for (CmisObject o : children) {
		        response.getWriter().print("<li>" + o.getName());
		        if (o instanceof Folder) {
		          response.getWriter().println(" createdBy: " + o.getCreatedBy() + "</li>");
		        } else {
		          Document doc = (Document) o;
		          response.getWriter().println(" createdBy: " + o.getCreatedBy() + " filesize: "
		                                     + doc.getContentStreamLength() + " bytes"
		                                     + "</li>");
		        }
		      }
		      response.getWriter().println("</ul>");
		    } catch (Exception e) {
		      throw new ServletException(e);
		    } finally {
		      response.getWriter().println("</body></html>");
		    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
