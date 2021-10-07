package p2ch02;

import java.io.Serializable;
import java.util.Map;
import java.util.StringJoiner;

public class TestData implements Serializable {

    private String header;
    private Map<String, String> props;

    public TestData(String header, Map<String, String> props) {
        this.header = header;
        this.props = props;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TestData.class.getSimpleName() + "[", "]")
                .add("header='" + header + "'")
                .add("props=" + props)
                .toString();
    }
}
