/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httputils;

/**
 *
 * @author orcl
 */
public class Header {

    String id;
    String title;
    String toPage;

    public Header() {
    }

    public Header(String id, String title, String toPage) {
        this.id = id;
        this.title = title;
        this.toPage = toPage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToPage() {
        return toPage;
    }

    public void setToPage(String toPage) {
        this.toPage = toPage;
    }

}
