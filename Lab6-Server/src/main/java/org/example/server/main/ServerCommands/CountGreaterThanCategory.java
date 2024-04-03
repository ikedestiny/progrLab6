package org.example.server.main.ServerCommands;


import org.example.common.data.AstartesCategory;
import org.example.common.data.SpaceMarine;
import org.example.common.util.ClientRequest;
import org.example.common.util.CollectionManager;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;

public class CountGreaterThanCategory extends StringArgumentCommand {
    private final CollectionManager collectionManager;
    private int count;
    //Scanner scanner = new Scanner(System.in);

    public CountGreaterThanCategory(CollectionManager collectionManager) {
        super("CountGreaterThanCategory",
                "display the number of elements whose category field value is greater than the specified one");
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerResponse execute(Object arg) {
        count = 0;
        ClientRequest clientRequest = (ClientRequest) arg;
        String argument = (String) clientRequest.getArgument();

        for (AstartesCategory as : AstartesCategory.values()) {
            if (as.name().equalsIgnoreCase(argument.trim())) {
                AstartesCategory s = as;
                for (SpaceMarine sp : this.collectionManager.getPriorityQueue()) {
                    if (sp.getCategory().ordinal() > s.ordinal()) {
                        count++;
                    }
                }
                System.out.println(count);
                return response();
            }
        }
        System.out.println("NO SUCH CATEGORY");
        return new ServerResponse(Status.ERROR, "No Such Category", null);
    }

    @Override
    public ServerResponse response() {
        return new ServerResponse(Status.SUCCESS, Integer.toString(count), null);

    }
}
