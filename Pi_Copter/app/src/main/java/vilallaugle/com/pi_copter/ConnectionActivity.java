package vilallaugle.com.pi_copter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
import java.net.URISyntaxException;

public class ConnectionActivity extends AppCompatActivity {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://172.28.82.44:2008");
        } catch (URISyntaxException e) {

        }
    }
    /*Socket socket = null;
    socket = new Socket("http://172.28.81.67",2004);
    DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
    DOS.writeUTF("HELLO_WORLD");
    socket.close();
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        mSocket.connect();
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clearMessage(View view) {
        EditText text = (EditText) findViewById(R.id.ipAddress);
        text.setText("");
        text = (EditText) findViewById(R.id.port);
        text.setText("");
        String message = "Fuck this socket shit\n";
        mSocket.emit("new message", message);
    }

    private void attemptSend() {
        /*String message = mInputMessageView.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            return;
        }

        mInputMessageView.setText("");
        mSocket.emit("new message", message);*/
        System.out.println("ATTEMPTING TO SEND\n");
    }
}
