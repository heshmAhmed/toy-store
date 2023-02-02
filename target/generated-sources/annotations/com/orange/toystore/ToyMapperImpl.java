package com.orange.toystore;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-02T14:09:55+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class ToyMapperImpl implements ToyMapper {

    @Override
    public ToyEntity toyResponseToToyEntity(ToyResponse toyResponse) {
        if ( toyResponse == null ) {
            return null;
        }

        ToyEntity toyEntity = new ToyEntity();

        return toyEntity;
    }

    @Override
    public ToyResponse toyEntityToToy(ToyEntity toyEntity) {
        if ( toyEntity == null ) {
            return null;
        }

        ToyResponse toyResponse = new ToyResponse();

        return toyResponse;
    }
}
