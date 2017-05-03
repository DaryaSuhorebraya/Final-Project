package by.epam.movierating.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.Writer;

/**
 * Created by Даша on 25.04.2017.
 */
public class PaginationTag extends SimpleTagSupport {
    private String uri;
    private String previous = "Previous";
    private String next = "Next";
    private static final int PAGE_COUNT=3;
    private int currentPage;

    private Writer getWriter() {
        return getJspContext().getOut();
    }

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();

        try {
            out.write("<nav style=\"text-align: center;\">");
            out.write("<ul class=\"pagination\">");
            out.write(constructPrevLink());
            for (int i=0;i<PAGE_COUNT;i++){
                out.write(constructLink(i));
            }
            out.write(constructNextLink());
            out.write("</ul>");
            out.write("</nav>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in pagination tag", ex);
        }
    }
    private String constructPrevLink(){
        StringBuilder link = new StringBuilder("<li");
        if (currentPage<=1){
            link.append(" class=\"disabled\"");
        }
        link.append("><a href=").append(uri).append("&curPageNumber=").
                append(currentPage - 1).append(">").append(previous).append("</a></li>");

        return link.toString();
    }
    private String constructNextLink(){
        StringBuilder link = new StringBuilder("<li");
        if (currentPage>=PAGE_COUNT){
            link.append(" class=\"disabled\"");
        }
        link.append("><a href=").append(uri).append("&curPageNumber=").
                append(currentPage + 1).append(">").append(next).append("</a></li>");

        return link.toString();
    }

    private String constructLink(int page) {
        StringBuilder link = new StringBuilder("<li");
        if ((page+1)==currentPage){
            link.append(" class=\"active\"");
        }
        link.append("><a href=").append(uri).append("&curPageNumber=").
                append(page + 1).append(">").append(page + 1).append("</a></li>");

        return link.toString();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }



    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
