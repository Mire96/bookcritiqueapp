package transfer;
import java.io.Serializable;
import transfer.util.ResponseStatus;

public class Response implements Serializable{
    private ResponseStatus status;
    private Object data;
    private Object error;

    public Response() {
    }

    public Response(ResponseStatus status, Object data, Object error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
