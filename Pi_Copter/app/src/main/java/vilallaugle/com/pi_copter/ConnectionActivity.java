package vilallaugle.com.pi_copter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//import com.github.nkzawa.socketio.client.IO;
//import com.github.nkzawa.socketio.client.Socket;

//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
import java.net.URISyntaxException;

public class ConnectionActivity extends Activity {


    private Socket socket;
    private static final int SERVERPORT = 2008;
    private static final String SERVER_IP = "192.168.1.6";
   /* private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://172.28.109.111:2222");
        } catch (URISyntaxException e) {

        }
    }*/

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
        new Thread(new ClientThread()).start();
        //mSocket.connect();
        //mSocket.
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clearMessage(View view) {

        String message = "Fuck this socket shit\n";
        try {
            EditText et = (EditText) findViewById(R.id.ipAddress);
            String str = et.getText().toString();
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            System.out.println("YO, FUCK THIS SHIT 1");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("YO, FUCK THIS SHIT 2");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("YO, FUCK THIS SHIT 3 " + e.getMessage());
            e.printStackTrace();
        }
        EditText text = (EditText) findViewById(R.id.ipAddress);
        text.setText("");
        text = (EditText) findViewById(R.id.port);
        text.setText("");
        //mSocket.send("new message", message);
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


    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

                socket = new Socket(serverAddr, SERVERPORT);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }
}
