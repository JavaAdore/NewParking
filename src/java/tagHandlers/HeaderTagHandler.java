/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tagHandlers;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author orcl
 */
public class HeaderTagHandler extends SimpleTagSupport {

    private Object currentClient;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            StringBuilder myheader = new StringBuilder();
            myheader.append("<li ><a href=\"index.jsp\">Home</a></li> |");
             myheader.append("<li ><a href=\"searchProducts.jsp\">Search</a></li> |");
            if (currentClient != null) {
               // myheader.append(String.format("<li>Welcome %s</li> | ",((Client)currentClient).getUserName() ));

                myheader.append("<li><a href=\"updateProfile.jsp\">Update Profile</a></li> | ");
                myheader.append("<li><a href=\"SignOut\">Sign Out </a></li>");
            } else {
               myheader.append(String.format("<li><a href=''>Welcome Guest |</li> "));

                myheader.append("<li><a href=\"SignIn.jsp\">Sign In</a></li> | "
                        + "<li><a href=\"register.jsp\">Register</a></li> ");
            }

            out.print(myheader.toString());
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in HeaderTagHandler tag", ex);
        }
    }

    public void setCurrentClient(Object currentClient) 
    {
        this.currentClient = currentClient;
    }
}
