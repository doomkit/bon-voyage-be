package ctu.via.bonvoyage.interfaces.converter;

import com.github.dozermapper.core.DozerConverter;
import ctu.via.bonvoyage.interfaces.enums.TripTypeEnum;

public class StringEnumConverter extends DozerConverter<String, TripTypeEnum> {

    public StringEnumConverter() {
        super(String.class, TripTypeEnum.class);
    }

    @Override
    public TripTypeEnum convertTo(String s, TripTypeEnum tripTypeEnum) {
        if (s != null){
            for (TripTypeEnum typeEnum : TripTypeEnum.values()){
                if (s.equals(typeEnum.getValue())){
                    return typeEnum;
                }
            }
            return TripTypeEnum.valueOf(s);
        }

        return null;
    }

    @Override
    public String convertFrom(TripTypeEnum tripTypeEnum, String s) {
        if (tripTypeEnum != null){
            return tripTypeEnum.getValue();
        }

        return null;
    }
}