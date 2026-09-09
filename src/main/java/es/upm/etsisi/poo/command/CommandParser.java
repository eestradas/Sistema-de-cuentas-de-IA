package es.upm.etsisi.poo.command;

import es.upm.etsisi.poo.BillType;
import es.upm.etsisi.poo.Messages;
import es.upm.etsisi.poo.Utilities;
import es.upm.etsisi.poo.controller.AgentController;
import es.upm.etsisi.poo.controller.BillController;
import es.upm.etsisi.poo.controller.CallController;
import es.upm.etsisi.poo.controller.UserController;

public class CommandParser {

    private final UserController userController;
    private final CallController callController;
    private final AgentController agentController;
    private final BillController billController;

    public CommandParser(UserController userController, CallController callController, AgentController agentController, BillController billController) {
        this.userController = userController;
        this.callController = callController;
        this.agentController = agentController;
        this.billController = billController;
    }

    public Command parse(String line) {
        if (line == null || line.isBlank() || line.startsWith("#")) return null;

        String[] parts = Utilities.splitLine(line);
        if (parts.length < 1) {
            System.out.println(Messages.COMMAND_MALFORMED);
            return null;
        }

        String command = parts[0].toLowerCase();

        if (command.equals("help")) return new HelpCommand();

        if (parts.length < 2) {
            System.out.println(Messages.COMMAND_MALFORMED);
            return null;
        }

        String action = parts[1].toLowerCase();

        switch (command) {
            case "user":
                return parseUserCommand(action, parts);
            case "call":
                return parseCallCommand(action, parts);
            case "agent":
                return parseAgentCommand(action, parts);
            case "bill":
                return parseBillCommand(action, parts);
            default:
                System.out.println(Messages.COMMAND_UNKNOWN);
                return null;
        }
    }

    private Command parseUserCommand(String action, String[] parts) {

        if (!action.equals("add") || parts.length < 5) {
            System.out.println(Messages.COMMAND_MALFORMED);
            return null;
        }

        String dni = parts[2];
        String name = parts[3];
        String password = parts[4];
        String accountNumber = parts.length > 5 ? parts[5] : null;

        return new AddUserCommand(userController, dni, name, password, accountNumber);
    }

    private Command parseCallCommand(String action, String[] parts) {

        if (!action.equals("send") || parts.length < 4) {
            System.out.println(Messages.COMMAND_MALFORMED);
            return null;
        }

        String dni = parts[2];
        String text = parts[3];

        return new SendCallCommand(callController, dni, text);
    }

    private Command parseAgentCommand(String action, String[] parts) {
        switch (action) {
            case "add":
                if (parts.length < 4) {
                    System.out.println(Messages.COMMAND_MALFORMED);
                    return null;
                }
                String dni = parts[2];
                int agentId = Utilities.parseAgentId(parts[3]);
                if(agentId == -1) {
                    System.out.println(Messages.agentIdNotNumber(parts[3]));
                    return null;
                }
                return new AddAgentCommand(agentController, dni, agentId);

            case "send":
                if (parts.length < 5) {
                    System.out.println(Messages.COMMAND_MALFORMED);
                    return null;
                }
                String dniSend = parts[2];
                int agentIdSend = Utilities.parseAgentId(parts[3]);
                if(agentIdSend == -1) {
                    System.out.println(Messages.agentIdNotNumber(parts[3]));
                    return null;
                }
                String input = parts[4];
                return new SendAgentCommand(agentController, dniSend, agentIdSend, input);
            default:
                System.out.println(Messages.COMMAND_UNKNOWN);
                return null;
        }
    }

    private Command parseBillCommand(String action, String[] parts) {

        if (!action.equals("create") || parts.length < 4){
            System.out.println(Messages.COMMAND_MALFORMED);
            return null;
        }

        String dni = parts[2];
        BillType billType;
        try {
            billType = BillType.valueOf(parts[3].toUpperCase());
            return new CreateBillCommand(billController, dni, billType);
        } catch (IllegalArgumentException e) {
            System.out.println(Messages.unknownBillType(parts[3]));
            return null;
        }

    }
}
