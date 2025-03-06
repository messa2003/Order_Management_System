package presentation;

import connection.ConnectionFactory;

public class Main
{
    public static void main(String args[])
    {
        ConnectionFactory connector = new ConnectionFactory();
        View view = new View();
        view.setVisible(true);
        Controller controller = new Controller(view, connector);
    }
}
