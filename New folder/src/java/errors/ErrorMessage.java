/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors;

/**
 *
 * @author orcl
 */
public class ErrorMessage {
    
    String errorBody;
    public ErrorMessage()
    {
    
    }
    
    
    public ErrorMessage(String errorBody)
    {
        this.errorBody = errorBody;
    }

    public String getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(String errorBody) {
        this.errorBody = errorBody;
    }
    
}
