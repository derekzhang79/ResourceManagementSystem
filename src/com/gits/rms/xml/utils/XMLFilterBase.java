/*-- 

 Copyright (C) 2000 Brett McLaughlin & Jason Hunter.
 All rights reserved.
 
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions, and the following disclaimer.
 
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions, and the disclaimer that follows 
    these conditions in the documentation and/or other materials 
    provided with the distribution.

 3. The name "JDOM" must not be used to endorse or promote products
    derived from this software without prior written permission.  For
    written permission, please contact license@jdom.org.
 
 4. Products derived from this software may not be called "JDOM", nor
    may "JDOM" appear in their name, without prior written permission
    from the JDOM Project Management (pm@jdom.org).
 
 In addition, we request (but do not require) that you include in the 
 end-user documentation provided with the redistribution and/or in the 
 software itself an acknowledgement equivalent to the following:
     "This product includes software developed by the
      JDOM Project (http://www.jdom.org/)."
 Alternatively, the acknowledgment may be graphical using the logos 
 available at http://www.jdom.org/images/logos.

 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE JDOM AUTHORS OR THE PROJECT
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.

 This software consists of voluntary contributions made by many 
 individuals on behalf of the JDOM Project and was originally 
 created by Brett McLaughlin <brett@jdom.org> and 
 Jason Hunter <jhunter@jdom.org>.  For more information on the 
 JDOM Project, please see <http://www.jdom.org/>.
 
 */

package com.gits.rms.xml.utils;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * Adds convenience methods and lexical event filtering to base SAX2 Filter
 * implementation.
 * 
 * <i>Code and comments adapted from XMLWriter-0.2, written by David Megginson
 * and released into the public domain, without warranty.</i>
 * 
 * <p>
 * The convenience methods are provided so that clients do not have to create
 * empty attribute lists or provide empty strings as parameters; for example,
 * the method invocation
 * </p>
 * 
 * <pre>
 * w.startElement(&quot;foo&quot;);
 * </pre>
 * 
 * <p>
 * is equivalent to the regular SAX2 ContentHandler method
 * </p>
 * 
 * <pre>
 * w.startElement(&quot;&quot;, &quot;foo&quot;, &quot;&quot;, new AttributesImpl());
 * </pre>
 * 
 * <p>
 * Except that it is more efficient because it does not allocate a new empty
 * attribute list each time.
 * </p>
 * 
 * <p>
 * In fact, there is an even simpler convenience method, <var>dataElement</var>,
 * designed for writing elements that contain only character data.
 * </p>
 * 
 * <pre>
 * w.dataElement(&quot;greeting&quot;, &quot;Hello, world!&quot;);
 * </pre>
 * 
 * <p>
 * is equivalent to
 * </p>
 * 
 * <pre>
 * w.startElement(&quot;greeting&quot;);
 * w.characters(&quot;Hello, world!&quot;);
 * w.endElement(&quot;greeting&quot;);
 * </pre>
 * 
 * @see org.xml.sax.helpers.XMLFilterImpl
 */
public class XMLFilterBase extends XMLFilterImpl implements LexicalHandler {

    // //////////////////////////////////////////////////////////////////
    // Constructors.
    // //////////////////////////////////////////////////////////////////

    protected static final Attributes EMPTY_ATTS = new AttributesImpl();

    protected static final String[] LEXICAL_HANDLER_NAMES = {
        "http://xml.org/sax/properties/lexical-handler",
        "http://xml.org/sax/handlers/LexicalHandler" };

    // //////////////////////////////////////////////////////////////////
    // Convenience methods.
    // //////////////////////////////////////////////////////////////////

    private LexicalHandler lexicalHandler = null;

    /**
     * Construct an XML filter with no parent.
     * 
     * <p>
     * This filter will have no parent: you must assign a parent before you
     * start a parse or do any configuration with setFeature or setProperty.
     * </p>
     * 
     * @see org.xml.sax.XMLReader#setFeature
     * @see org.xml.sax.XMLReader#setProperty
     */
    public XMLFilterBase() {
    }

    /**
     * Create an XML filter with the specified parent.
     * 
     * <p>
     * Use the XMLReader provided as the source of events.
     * </p>
     * 
     * @param xmlreader
     *            The parent in the filter chain.
     */
    public XMLFilterBase(XMLReader parent) {
        super(parent);
    }

    /**
     * Add a string of character data, with XML escaping.
     * 
     * <p>
     * This is a convenience method that takes an XML String, converts it to a
     * character array, then invokes {@link @see
     * org.xml.sax.ContentHandler#characters}.
     * </p>
     * 
     * @param data
     *            The character data.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see @see org.xml.sax.ContentHandler#characters
     */
    public void characters(String data) throws SAXException {
        char ch[] = data.toCharArray();
        this.characters(ch, 0, ch.length);
    }

    /*
     * Filter a comment event.
     * 
     * @param ch An array holding the characters in the comment.
     * 
     * @param start The starting position in the array.
     * 
     * @param length The number of characters to use from the array.
     * 
     * @exception org.xml.sax.SAXException If a filter further down the chain
     * raises an exception.
     * 
     * @see org.xml.sax.ext.LexicalHandler#comment
     */
    @Override
    public void comment(char[] ch, int start, int length) throws SAXException {
        if (this.lexicalHandler != null) {
            this.lexicalHandler.comment(ch, start, length);
        }
    }

    /**
     * Add an element with character data content but no Namespace URI or qname.
     * 
     * <p>
     * This is a convenience method to add a complete element with character
     * data content, including the start tag and end tag. The method provides an
     * empty string for the Namespace URI, and empty string for the qualified
     * name. It invokes
     * {@link #dataElement(String, String, String, Attributes, String)}
     * directly.
     * </p>
     * 
     * @param localName
     *            The element's local name.
     * @param atts
     *            The element's attributes.
     * @param content
     *            The character data content.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     * @see #characters(String)
     * @see org.xml.sax.ContentHandler#endElement
     */
    public void dataElement(String localName, Attributes atts, String content) throws SAXException {
        this.dataElement("", localName, "", atts, content);
    }

    /**
     * Add an element with character data content but no attributes or Namespace
     * URI.
     * 
     * <p>
     * This is a convenience method to add a complete element with character
     * data content, including the start tag and end tag. The method provides an
     * empty string for the Namespace URI, and empty string for the qualified
     * name, and an empty attribute list. It invokes
     * {@link #dataElement(String, String, String, Attributes, String)}
     * directly.
     * </p>
     * 
     * @param localName
     *            The element's local name.
     * @param content
     *            The character data content.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     * @see #characters(String)
     * @see org.xml.sax.ContentHandler#endElement
     */
    public void dataElement(String localName, String content) throws SAXException {
        this.dataElement("", localName, "", EMPTY_ATTS, content);
    }

    /**
     * Add an element with character data content but no qname or attributes.
     * 
     * <p>
     * This is a convenience method to add a complete element with character
     * data content, including the start tag and end tag. This method provides
     * an empty string for the qname and an empty attribute list. It invokes
     * {@link #dataElement(String, String, String, Attributes, String)}
     * directly.
     * </p>
     * 
     * @param uri
     *            The element's Namespace URI.
     * @param localName
     *            The element's local name.
     * @param content
     *            The character data content.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     * @see #characters(String)
     * @see org.xml.sax.ContentHandler#endElement
     */
    public void dataElement(String uri, String localName, String content) throws SAXException {
        this.dataElement(uri, localName, "", EMPTY_ATTS, content);
    }

    /**
     * Add an element with character data content.
     * 
     * <p>
     * This is a convenience method to add a complete element with character
     * data content, including the start tag and end tag.
     * </p>
     * 
     * <p>
     * This method invokes {@link @see org.xml.sax.ContentHandler#startElement},
     * followed by {@link #characters(String)}, followed by {@link @see
     * org.xml.sax.ContentHandler#endElement}.
     * </p>
     * 
     * @param uri
     *            The element's Namespace URI.
     * @param localName
     *            The element's local name.
     * @param qName
     *            The element's default qualified name.
     * @param atts
     *            The element's attributes.
     * @param content
     *            The character data content.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     * @see #characters(String)
     * @see org.xml.sax.ContentHandler#endElement
     */
    public void dataElement(String uri, String localName, String qName, Attributes atts, String content) throws SAXException {
        this.startElement(uri, localName, qName, atts);
        this.characters(content);
        this.endElement(uri, localName, qName);
    }

    /**
     * Add an empty element without a Namespace URI, qname or attributes.
     * 
     * <p>
     * This method will supply an empty string for the qname, and empty string
     * for the Namespace URI, and an empty attribute list. It invokes
     * {@link #emptyElement(String, String, String, Attributes)} directly.
     * </p>
     * 
     * @param localName
     *            The element's local name.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see #emptyElement(String, String, String, Attributes)
     */
    public void emptyElement(String localName) throws SAXException {
        this.emptyElement("", localName, "", EMPTY_ATTS);
    }

    /**
     * Add an empty element without a Namespace URI or qname.
     * 
     * <p>
     * This method will provide an empty string for the Namespace URI, and empty
     * string for the qualified name. It invokes
     * {@link #emptyElement(String, String, String, Attributes)} directly.
     * </p>
     * 
     * @param localName
     *            The element's local name.
     * @param atts
     *            The element's attribute list.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     */
    public void emptyElement(String localName, Attributes atts) throws SAXException {
        this.emptyElement("", localName, "", atts);
    }

    /**
     * Add an empty element without a qname or attributes.
     * 
     * <p>
     * This method will supply an empty string for the qname and an empty
     * attribute list. It invokes
     * {@link #emptyElement(String, String, String, Attributes)} directly.
     * </p>
     * 
     * @param uri
     *            The element's Namespace URI.
     * @param localName
     *            The element's local name.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see #emptyElement(String, String, String, Attributes)
     */
    public void emptyElement(String uri, String localName) throws SAXException {
        this.emptyElement(uri, localName, "", EMPTY_ATTS);
    }

    /**
     * Add an empty element.
     * 
     * Both a {@link #startElement startElement} and an {@link #endElement
     * endElement} event will be passed on down the filter chain.
     * 
     * @param uri
     *            The element's Namespace URI, or the empty string if the
     *            element has no Namespace or if Namespace processing is not
     *            being performed.
     * @param localName
     *            The element's local name (without prefix). This parameter must
     *            be provided.
     * @param qName
     *            The element's qualified name (with prefix), or the empty
     *            string if none is available. This parameter is strictly
     *            advisory: the writer may or may not use the prefix attached.
     * @param atts
     *            The element's attribute list.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     * @see org.xml.sax.ContentHandler#endElement
     */
    public void emptyElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        this.startElement(uri, localName, qName, atts);
        this.endElement(uri, localName, qName);
    }

    /*
     * Filter a end CDATA event.
     * 
     * @exception org.xml.sax.SAXException If a filter further down the chain
     * raises an exception.
     * 
     * @see org.xml.sax.ext.LexicalHandler#endCDATA
     */
    @Override
    public void endCDATA() throws SAXException {
        if (this.lexicalHandler != null) {
            this.lexicalHandler.endCDATA();
        }
    }

    // //////////////////////////////////////////////////////////////////
    // Override org.xml.sax.helpers.XMLFilterImpl methods.
    // //////////////////////////////////////////////////////////////////

    /**
     * Filter a end DTD event.
     * 
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ext.LexicalHandler#endDTD
     */
    @Override
    public void endDTD() throws SAXException {
        if (this.lexicalHandler != null) {
            this.lexicalHandler.endDTD();
        }
    }

    /**
     * End an element without a Namespace URI or qname.
     * 
     * <p>
     * This method will supply an empty string for the qName and an empty string
     * for the Namespace URI. It invokes
     * {@link #endElement(String, String, String)} directly.
     * </p>
     * 
     * @param localName
     *            The element's local name.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#endElement
     */
    public void endElement(String localName) throws SAXException {
        this.endElement("", localName, "");
    }

    /**
     * End an element without a qname.
     * 
     * <p>
     * This method will supply an empty string for the qName. It invokes
     * {@link #endElement(String, String, String)} directly.
     * </p>
     * 
     * @param uri
     *            The element's Namespace URI.
     * @param localName
     *            The element's local name.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#endElement
     */
    public void endElement(String uri, String localName) throws SAXException {
        this.endElement(uri, localName, "");
    }

    // //////////////////////////////////////////////////////////////////
    // Registration of org.xml.sax.ext.LexicalHandler.
    // //////////////////////////////////////////////////////////////////

    /*
     * Filter a end entity event.
     * 
     * @param name The name of the entity that is ending.
     * 
     * @exception org.xml.sax.SAXException If a filter further down the chain
     * raises an exception.
     * 
     * @see org.xml.sax.ext.LexicalHandler#endEntity
     */
    @Override
    public void endEntity(String name) throws SAXException {
        if (this.lexicalHandler != null) {
            this.lexicalHandler.endEntity(name);
        }
    }

    /**
     * Get the current lexical handler.
     * 
     * @return The current lexical handler, or null if none was set.
     */
    public LexicalHandler getLexicalHandler() {
        return this.lexicalHandler;
    }

    // //////////////////////////////////////////////////////////////////
    // Implementation of org.xml.sax.ext.LexicalHandler.
    // //////////////////////////////////////////////////////////////////

    /**
     * Look up the value of a property.
     * 
     * @param name
     *            The property name.
     * @return The current value of the property.
     * @exception org.xml.sax.SAXNotRecognizedException
     *                When the XMLReader does not recognize the feature name.
     * @exception org.xml.sax.SAXNotSupportedException
     *                When the XMLReader recognizes the property name but cannot
     *                determine its value at this time.
     * @see org.xml.sax.XMLReader#setFeature
     */
    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
            if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
                return this.getLexicalHandler();
            }
        }
        return super.getProperty(name);
    }

    /**
     * Installs lexical handler before a parse.
     * 
     * <p>
     * Before every parse, check whether the parent is non-null, and re-register
     * the filter for the lexical events.
     * </p>
     */
    private void installLexicalHandler() {
        XMLReader parent = this.getParent();
        if (parent == null) {
            throw new NullPointerException("No parent for filter");
        }
        // try to register for lexical events
        for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
            try {
                parent.setProperty(LEXICAL_HANDLER_NAMES[i], this);
                break;
            } catch (SAXNotRecognizedException ex) {
                // ignore
            } catch (SAXNotSupportedException ex) {
                // ignore
            }
        }
    }

    /**
     * Parse a document.
     * 
     * @param input
     *            The input source for the document entity.
     * @exception org.xml.sax.SAXException
     *                Any SAX exception, possibly wrapping another exception.
     * @exception java.io.IOException
     *                An IO exception from the parser, possibly from a byte
     *                stream or character stream supplied by the application.
     * @see org.xml.sax.XMLReader#parse(org.xml.sax.InputSource)
     */
    @Override
    public void parse(InputSource input) throws SAXException, IOException {
        this.installLexicalHandler();
        super.parse(input);
    }

    /**
     * Set the lexical handler.
     * 
     * @param handler
     *            The new lexical handler.
     * @exception java.lang.NullPointerException
     *                If the handler is null.
     */
    public void setLexicalHandler(LexicalHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null lexical handler");
        } else {
            this.lexicalHandler = handler;
        }
    }

    /**
     * Set the value of a property.
     * 
     * <p>
     * This will always fail if the parent is null.
     * </p>
     * 
     * @param name
     *            The property name.
     * @param state
     *            The requested property value.
     * @exception org.xml.sax.SAXNotRecognizedException
     *                When the XMLReader does not recognize the property name.
     * @exception org.xml.sax.SAXNotSupportedException
     *                When the XMLReader recognizes the property name but cannot
     *                set the requested value.
     * @see org.xml.sax.XMLReader#setProperty
     */
    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
            if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
                this.setLexicalHandler((LexicalHandler) value);
                return;
            }
        }
        super.setProperty(name, value);
    }

    /*
     * Filter a start CDATA event.
     * 
     * @exception org.xml.sax.SAXException If a filter further down the chain
     * raises an exception.
     * 
     * @see org.xml.sax.ext.LexicalHandler#startCDATA
     */
    @Override
    public void startCDATA() throws SAXException {
        if (this.lexicalHandler != null) {
            this.lexicalHandler.startCDATA();
        }
    }

    /**
     * Filter a start DTD event.
     * 
     * @param name
     *            The document type name.
     * @param publicId
     *            The declared public identifier for the external DTD subset, or
     *            null if none was declared.
     * @param systemId
     *            The declared system identifier for the external DTD subset, or
     *            null if none was declared.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ext.LexicalHandler#startDTD
     */
    @Override
    public void startDTD(String name, String publicId, String systemId) throws SAXException {
        if (this.lexicalHandler != null) {
            this.lexicalHandler.startDTD(name, publicId, systemId);
        }
    }

    // //////////////////////////////////////////////////////////////////
    // Internal methods.
    // //////////////////////////////////////////////////////////////////

    /**
     * Start a new element without a Namespace URI, qname, or attributes.
     * 
     * <p>
     * This method will provide an empty string for the Namespace URI, and empty
     * string for the qualified name, and a default empty attribute list. It
     * invokes {@link #startElement(String, String, String, Attributes)}
     * directly.
     * </p>
     * 
     * @param localName
     *            The element's local name.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     */
    public void startElement(String localName) throws SAXException {
        this.startElement("", localName, "", EMPTY_ATTS);
    }

    // //////////////////////////////////////////////////////////////////
    // Internal state.
    // //////////////////////////////////////////////////////////////////

    /**
     * Start a new element without a Namespace URI or qname.
     * 
     * <p>
     * This method will provide an empty string for the Namespace URI, and empty
     * string for the qualified name. It invokes
     * {@link #startElement(String, String, String, Attributes)} directly.
     * </p>
     * 
     * @param localName
     *            The element's local name.
     * @param atts
     *            The element's attribute list.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     */
    public void startElement(String localName, Attributes atts) throws SAXException {
        this.startElement("", localName, "", atts);
    }

    // //////////////////////////////////////////////////////////////////
    // Constants.
    // //////////////////////////////////////////////////////////////////

    /**
     * Start a new element without a qname or attributes.
     * 
     * <p>
     * This method will provide a default empty attribute list and an empty
     * string for the qualified name. It invokes
     * {@link #startElement(String, String, String, Attributes)} directly.
     * </p>
     * 
     * @param uri
     *            The element's Namespace URI.
     * @param localName
     *            The element's local name.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     */
    public void startElement(String uri, String localName) throws SAXException {
        this.startElement(uri, localName, "", EMPTY_ATTS);
    }

    /*
     * Filter a start entity event.
     * 
     * @param name The name of the entity. If it is a parameter entity, the name
     * will begin with '%', and if it is the external DTD subset, it will be
     * "[dtd]".
     * 
     * @exception org.xml.sax.SAXException If a filter further down the chain
     * raises an exception.
     * 
     * @see org.xml.sax.ext.LexicalHandler#startEntity
     */
    @Override
    public void startEntity(String name) throws SAXException {
        if (this.lexicalHandler != null) {
            this.lexicalHandler.startEntity(name);
        }
    }

}

// end of XMLFilterBase.java
