package com.degloba.MotorBusqueda;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ISOLatin1AccentFilter;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;



public class AnalizerPersonalitzat extends Analyzer {
    public TokenStream reusableTokenStream(
        String fieldName, Reader reader) throws IOException {

        SavedStreams streams =
            (SavedStreams) getPreviousTokenStream();

        if (streams == null) {
            streams = new SavedStreams();
            setPreviousTokenStream(streams);

            streams.tokenizer = new StandardTokenizer(null, reader);
            streams.stream = new StandardFilter(streams.tokenizer);
            //streams.stream = new LengthTokenFilter(streams.stream, 3);
            streams.stream = new LowerCaseFilter(streams.stream);
            streams.stream = new ISOLatin1AccentFilter(streams.stream);
        } else {
            streams.tokenizer.reset(reader);
        }

        return streams.stream;
    }

    private class SavedStreams {
        Tokenizer tokenizer;
        TokenStream stream;
    }

    public TokenStream tokenStream(
        String fieldName, Reader reader) {

        Tokenizer tokenizer = new StandardTokenizer(null, reader);
        TokenStream stream = new StandardFilter(tokenizer);
        //TokenStream stream = new LengthTokenFilter(stream, 3);
        stream = new LowerCaseFilter(stream);
        stream = new ISOLatin1AccentFilter(stream);
        
        return stream;
    }
}
