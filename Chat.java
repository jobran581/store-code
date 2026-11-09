package finalproject;

import java.io.*;
import java.util.ArrayList;

public class Chat implements Serializable {

    private Customer customer;
    private CustomerServiceWorkker worker;
    private Manager manager;
    private ArrayList<Message> messages;

    private boolean forwardedToManager;
    private boolean refundRequested;
    private double refundAmount;

    // Constructor: Initializes a chat between customer and customer service worker.
    public Chat(Customer customer, CustomerServiceWorkker worker) throws StoreException {
        if (customer == null)
            throw new StoreException("Customer null");

        if (worker == null)
            throw new StoreException("Worker null");

        this.customer = customer;
        this.worker = worker;
        this.messages = new ArrayList<Message>();
        this.forwardedToManager = false;
        this.refundRequested = false;
        this.refundAmount = 0;
    }

    // Adds a customer message to the chat.
    public void sendCustomerMessage(String text) throws StoreException {
        messages.add(new Message(customer.getName(), text));
    }

    // Adds a worker message to the chat.
    public void sendWorkerMessage(String text) throws StoreException {
        messages.add(new Message(worker.getName(), text));
    }

    // Forwards the chat to a manager with a specified reason.
    public void forwardToManager(Manager manager, String reason) throws StoreException {
        if (manager == null)
            throw new StoreException("Manager not found");

        this.manager = manager;
        this.forwardedToManager = true;

        messages.add(new Message("SYSTEM", "Chat forwarded to manager. Reason: " + reason));
    }

    // Requests a refund with specified amount, requires manager forwarding first.
    public void requestRefund(double amount) throws StoreException {
        if (!forwardedToManager)
            throw new StoreException("Refund must be forwarded to manager first");

        if (amount <= 0)
            throw new StoreException("Refund amount must be positive");

        refundRequested = true;
        refundAmount = amount;

        messages.add(new Message("SYSTEM", "Refund requested: " + amount));
    }

    // Manager approves a full refund and records it in the chat.
    public void approveRefund(Manager manager) throws StoreException {
        if (manager == null)
            throw new StoreException("Only manager can approve refund");

        if (!refundRequested)
            throw new StoreException("No refund requested");

        messages.add(new Message(manager.getName(), "Refund approved: " + refundAmount));
    }

    // Manager approves a half refund (50%) and records it in the chat.
    public void approveHalfRefund(Manager manager) throws StoreException {
        if (manager == null)
            throw new StoreException("Only manager can approve refund");

        if (!refundRequested)
            throw new StoreException("No refund requested");

        messages.add(new Message(manager.getName(), "Half refund approved: " + (refundAmount / 2)));
    }

    // Returns formatted chat, accessible only to customer or worker involved.
    public String printChatForCustomerOrWorker(String viewerName) throws StoreException {
        if (!viewerName.equalsIgnoreCase(customer.getName())
                && !viewerName.equalsIgnoreCase(worker.getName())) {
            throw new StoreException("You are not allowed to view this chat");
        }

        return printChat();
    }

    // Returns full chat, accessible only to managers.
    public String printChatForManager(Manager manager) throws StoreException {
        if (manager == null)
            throw new StoreException("Only manager can view all chats");

        return printChat();
    }

    // Returns the complete chat log with all messages.
    public String printChat() {
        String result = "===== CHAT LOG =====\n";
        result += "Customer: " + customer.getName() + "\n";
        result += "Worker: " + worker.getName() + "\n";

        if (forwardedToManager && manager != null)
            result += "Manager: " + manager.getName() + "\n";

        result += "\n";

        for (Message m : messages) {
            result += m + "\n";
        }

        return result;
    }
}
