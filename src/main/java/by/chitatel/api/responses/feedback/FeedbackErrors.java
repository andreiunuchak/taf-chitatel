package by.chitatel.api.responses.feedback;

import java.util.List;

public class FeedbackErrors {

    private List<String> message_name;
    private List<String> message_phone;
    private List<String> message_note;

    public List<String> getMessageNameError() {
        return message_name;
    }

    public List<String> getMessagePhoneError() {
        return message_phone;
    }

    public List<String> getMessageNoteError() {
        return message_note;
    }
}
