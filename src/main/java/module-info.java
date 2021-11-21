module corejava {
    requires java.xml;
    requires java.logging;
    requires java.xml.crypto;
    requires java.sql;
    requires java.naming;

    requires mockito.all;
    requires org.jsoup;
    requires org.antlr.antlr4.runtime;
    requires org.postgresql.jdbc;
    requires fluentjdbc;

    opens p2ch05.db to fluentjdbc;
}