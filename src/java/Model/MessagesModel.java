/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author MITICC06
 */
public class MessagesModel {

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    public MessagesModel(String title, String text, String type) {
        this.title = title;
        this.text = text;
        this.type = type;
    }
    private String title;
    private String text;
    private String type;

    public static final String ATT_STORE = "messages";
    public static final String ATT_TYPE_INFO = "info";
    public static final String ATT_TYPE_SUCCESS = "success";
    public static final String ATT_TYPE_ERROR = "error";
    public static final String ATT_TYPE_DARK = "dark";
    public static final String ATT_TYPE_NOTICE = "notice";

    ////req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Tieu de","noi Dung",MessagesModel.ATT_TYPE_INFO));

}
