
package com.lit.lms.model;

public class VerifyPayStackTransaction
{
    /**
     * this status is "true" if the request is successful and false if is not NOTE: This does not mean the transaction was successful, data.status holds that information
     */
    private String status;
    /**
     * information about the request, could be "verification successful" or "invalid key"
     */
    private String message;
    /**
     * contains details about the transaction
     */
    private PayStackData data;

    public VerifyPayStackTransaction()
    {
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public PayStackData getData()
    {
        return data;
    }

    public void setData(PayStackData data)
    {
        this.data = data;
    }

}