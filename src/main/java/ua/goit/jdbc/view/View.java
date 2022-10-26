package ua.goit.jdbc.view;

public interface View {
    String read();

    Runnable write(String message);
}
