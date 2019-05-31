package utils;

import java.sql.Connection;

public abstract class BDConnection {
    protected Connection con;
    protected String servidor;
    protected String bd;
    protected String usuario;
    protected String senha;
    protected String driver;
    protected int porta;

    public Connection getConnection() {
        return null;
    }

    public abstract  String getURL();
}
