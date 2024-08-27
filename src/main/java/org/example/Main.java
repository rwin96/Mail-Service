package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Khodam ba in test kardam ino gozashtam ke halate defaultesh bashe
        EmailSettings settings = EmailSettings.create("smtp.gmail.com", EncryptionType.SSL, "aryaei13833@gmail.com", "vzndknygjfxlsggj");

        EmailService emailService = new EmailService();

        do {
            System.out.println("Please choose an option:");
            System.out.println("1. Send email");
            System.out.println("2. Set up email settings");
            System.out.println("3. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Please enter your email subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Please enter your email message: ");
                    String message = scanner.nextLine();
                    System.out.print("Please enter receiver email: ");
                    String recipient = scanner.nextLine();


                    try {
                        emailService.sendEmail(subject, message, recipient);
                        System.out.println();
                        System.out.println("Email sent successfully.");
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println("Failed to send email: " + e.getMessage());
                    }

                    break;
                case 2:
                    System.out.print("Please enter server address: ");
                    String host = scanner.next();
                    System.out.print("Please enter server port: ");
                    String port = scanner.next();
                    System.out.println("Please select encryption type: ");
                    System.out.println("1-TLS");
                    System.out.println("2-SSL");
                    System.out.println("3-Plain");
                    String type = scanner.next();
                    EncryptionType encryptionType = null;
                    if (type.trim().strip().equalsIgnoreCase("3")) encryptionType = EncryptionType.Plain;
                    else if (type.trim().strip().equalsIgnoreCase("2")) encryptionType = EncryptionType.SSL;
                    else encryptionType = EncryptionType.TLS;
                    System.out.print("Please enter your email username: ");
                    String email = scanner.next();
                    System.out.print("Please enter your email password: ");
                    String password = scanner.next();

                    EmailSettings.update(host, encryptionType, email, password);
                    System.out.println();
                    System.out.println("Done");
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (true);

    }
}