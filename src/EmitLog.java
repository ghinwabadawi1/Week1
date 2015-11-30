/**
 * Created by eghibad on 11/30/15.
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv)
            throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("142.133.111.127");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = getMessage();

        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

    private static String getMessage(){
        System.out.println("Enter message here : ");
        String message;
        Scanner scanIn = new Scanner(System.in);
        message = scanIn.nextLine();
        scanIn.close();
        System.out.println("message === " + message);
        return message;

//        if (strings.length < 1)
//            return "Hello World! No Arguments";
//        return joinStrings(strings, " ");
    }
}
