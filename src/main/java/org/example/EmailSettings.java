package org.example;

public class EmailSettings {

    private static EmailSettings instance;
    private String serverAddress;
    private int port;
    private EncryptionType encryptionType;
    private String username;
    private String password;

    private EmailSettings() {}

    public static EmailSettings getInstance() {
        if (instance == null) {
            instance = new EmailSettings();
        }
        return instance;
    }

    // CRUD
    public static EmailSettings create(String serverAddress, EncryptionType encryptionType, String username, String password) {
        EmailService.setFlag(true);
        EmailSettings settings = getInstance();
        settings.setServerAddress(serverAddress);
        settings.setEncryptionType(encryptionType);
        if (settings.getEncryptionType() == EncryptionType.Plain) {
            settings.setPort(25);
        } else if (settings.getEncryptionType() == EncryptionType.SSL) {
            settings.setPort(465);
        } else {
            settings.setPort(587);
        }
        settings.setUsername(username);
        settings.setPassword(password);
        return settings;
    }

    public static EmailSettings read() {
        return getInstance();
    }

    public static EmailSettings update(String serverAddress, EncryptionType encryptionType, String username, String password) {
        EmailService.setFlag(true);
        EmailSettings settings = getInstance();
        settings.setServerAddress(serverAddress);
        if (settings.getEncryptionType() == EncryptionType.Plain) {
            settings.setPort(25);
        } else if (settings.getEncryptionType() == EncryptionType.SSL) {
            settings.setPort(465);
        } else {
            settings.setPort(587);
        }
        settings.setEncryptionType(encryptionType);
        settings.setUsername(username);
        settings.setPassword(password);
        return settings;
    }

    public static void delete() {
        instance = null;
    }


    // Setters & Getters
    public String getServerAddress() {
        return serverAddress;
    }

    private void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getPort() {
        return port;
    }

    private void setPort(int port) {
        this.port = port;
    }

    public EncryptionType getEncryptionType() {
        return encryptionType;
    }

    private void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
