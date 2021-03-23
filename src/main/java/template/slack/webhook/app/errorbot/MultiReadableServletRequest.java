package template.slack.webhook.app.errorbot;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * HttpServlet Request의 Body는 InputStream이다.
 * Stream이기 때문에 한번 읽으면 다시 사용불가능하다.
 * 그렇기 떄문에 복사가 필요하다.
 * HttpServletRequest의 InputStream을 ByteArrayOutputStream에 복사한다.
 */
public class MultiReadableServletRequest extends HttpServletRequestWrapper {
    private ByteArrayOutputStream copiedOutputStream = null;

    public MultiReadableServletRequest(HttpServletRequest request) {
        super(request);
    }

    /**
     * InputStream을 복사하여, OutputStream에 복사 하여 저장
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (copiedOutputStream == null) {
            copiedOutputStream = new ByteArrayOutputStream();
            IOUtils.copy(super.getInputStream(), copiedOutputStream);
        }
        return new CachedServletInputStream();
    }

    /**
     * 복사된 copiedOutputStream을 이용해서 필요할 때마다 InputStream으로 반환
     */
    public class CachedServletInputStream extends ServletInputStream {
        private ByteArrayInputStream input;

        public CachedServletInputStream() {
            input = new ByteArrayInputStream(copiedOutputStream.toByteArray());
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() throws IOException {
            return input.read();
        }
    }

}
