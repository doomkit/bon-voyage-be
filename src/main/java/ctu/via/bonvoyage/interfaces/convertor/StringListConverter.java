package ctu.via.bonvoyage.interfaces.convertor;

import com.github.dozermapper.core.DozerConverter;

import java.util.Arrays;
import java.util.List;

public class StringListConverter extends DozerConverter<String, List> {

    public StringListConverter() {
        super(String.class, List.class);
    }

    @Override
    public List<String> convertTo(String s, List list) {
        if (s != null){
            list = Arrays.asList(s.split(";;;"));
            return list;
        }

        return null;
    }

    @Override
    public String convertFrom(List list, String s) {
        if (list != null && !list.isEmpty()){
            s = String.join(";;;", list);
            return s;
        }

        return null;
    }
}