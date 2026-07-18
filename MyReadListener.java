package EJPracts;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
public class MyReadListener implements ReadListener {
private ServletInputStream iStream = null;
private AsyncContext cnt = null;
public MyReadListener(ServletInputStream in, AsyncContext ac) {
this.iStream = in;
this.cnt = ac;
}
@Override
public void onDataAvailable() {
try {
StringBuilder sb = new StringBuilder();
int len;
byte[] b = new byte[1024];
while (iStream.isReady() && (len = iStream.read(b)) != -1) {
String mydata = new String(b, 0, len);
sb.append(mydata);
}
} catch (IOException ex) {
Logger.getLogger(MyReadListener.class.getName()).log(Level.SEVERE, null, ex);
}
}

@Override
public void onAllDataRead() {
System.out.println("Called onAllDataRead()");
cnt.complete();
}
@Override
public void onError(Throwable t) {
Logger.getLogger(MyReadListener.class.getName()).log(Level.SEVERE, null, t);
cnt.complete();
}
}