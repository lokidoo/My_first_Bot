package com.example;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

  @Override
  public String getBotUsername() {
      return "Maxim_Petuh_Bot";
  }

  @Override
  public String getBotToken() {
      return "7835081043:AAEiPhx0Kus5byO8O2jjhJLZSnOkDJDXTTQ";
  }

   @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();
        var msgId = msg.getMessageId();
    
        copyMessage(id, msgId);

        System.out.println(user.getFirstName() + ": " + msg.getText());
}

public void sendText(Long who, String what){
    SendMessage sm = SendMessage.builder()
                     .chatId(who.toString()) 
                     .text(what).build();   
    try {
         execute(sm);                        
    } catch (TelegramApiException e) {
         throw new RuntimeException(e);      
    }
 }

 public void copyMessage(Long who, Integer msgId){
   CopyMessage cm = CopyMessage.builder()
              .fromChatId(who.toString())  //We copy from the user
           .chatId(who.toString())      //And send it back to him
           .messageId(msgId)            //Specifying what message
           .build();
    try {
        execute(cm);
    } catch (TelegramApiException e) {
        throw new RuntimeException(e);
    }
}

}