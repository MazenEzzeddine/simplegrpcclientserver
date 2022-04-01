import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MessageClient {

    public static void main(String[] args) {
        callForInbox();

    }


    private static void callForInbox() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 5002)
                .usePlaintext()
                .build();

        MessageServiceGrpc.MessageServiceBlockingStub  messageServiceBlockingStub = MessageServiceGrpc.newBlockingStub(managedChannel);
        Username username = Username.newBuilder().setUsername("Ali")
                .build();
        System.out.println("connected to server ");
        InboxReply reply = messageServiceBlockingStub.inbox(username);

        for (Message m : reply.getMessagesList())
            System.out.println(m.toString());

    }




}
