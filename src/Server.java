/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author qwerty
 */

    import java.net.*;
import java.io.*;
public class Server {
   public static void main(String[] ar)    {
     int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
       try {
         ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
         System.out.println("Ожидаем подключение клиента...");

         Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
         System.out.println("Клиент подключился :) ... Наконец-то!");
         System.out.println();

 // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту. 
         InputStream sin = socket.getInputStream();
         OutputStream sout = socket.getOutputStream();

 // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
         DataInputStream in = new DataInputStream(sin);
         DataOutputStream out = new DataOutputStream(sout);

         String line = null;
         while(true) {
           line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
           System.out.println("Клиент прислал : " + line);
           System.out.println("Отвечу ему тоже самое...");
           out.writeUTF("И тебе " + line); // отсылаем клиенту обратно.
           out.flush(); // заставляем поток закончить передачу данных.
           System.out.println("Ждем ещё сообщения...");
           System.out.println();
         }
      } catch(Exception x) { x.printStackTrace(); }
   }
}

