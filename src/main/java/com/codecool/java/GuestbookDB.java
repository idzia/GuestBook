package com.codecool.java;

import java.util.ArrayList;
import java.util.List;

public class GuestbookDB {
    private List<Message> messagesList;

    public GuestbookDB() {
        messagesList = new ArrayList<Message>();
        Message message1 = new Message("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Aliquam at augue faucibus, fermentum sem at, egestas nibh. " +
                "Orci varius natoque penatibus et magnis dis parturient montes, " +
                "nascetur ridiculus mus.", "Ania", "12.07.2018");
        Message message2 = new Message("Nulla ut porta quam. " +
                "Nulla lacinia, sapien sit amet porta hendrerit, lacus tellus efficitur lectus, " +
                "consequat varius libero risus non urna.", "Amelia", "13.07.2018");
        Message message3 = new Message("Phasellus risus ante, ultrices ut auctor non, " +
                "pulvinar non orci.", "Tymon", "14.07.2018");
        messagesList.add(message1);
        messagesList.add(message2);
        messagesList.add(message3);

    }

    public List<Message> getMessageList() {
        return messagesList;
    }
}
