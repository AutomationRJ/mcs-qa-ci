package com.tivo.common;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.joda.time.DateTime;

/**
 * Created by rjaiswal on 20-Jun-17.
 */
public class DateTimeCodec implements Codec<DateTime>
{
    @Override
    public DateTime decode(BsonReader reader, DecoderContext decoderContext )
    {
        return new DateTime( reader.readDateTime() );
    }

    @Override
    public void encode(BsonWriter writer, DateTime value, EncoderContext encoderContext )
    {
        writer.writeDateTime( value.getMillis() );
    }

    @Override
    public Class<DateTime> getEncoderClass()
    {
        return DateTime.class;
    }
}
