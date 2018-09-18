CLSS javax.mail.StoreClosedException
supr class javax.mail.MessagingException
cons public javax.mail.StoreClosedException(javax.mail.Store)
cons public javax.mail.StoreClosedException(javax.mail.Store,java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
meth public javax.mail.Store getStore()
CLSS javax.mail.event.ConnectionAdapter
supr class java.lang.Object
intf interface javax.mail.event.ConnectionListener
cons public javax.mail.event.ConnectionAdapter()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void opened(javax.mail.event.ConnectionEvent)
meth public void disconnected(javax.mail.event.ConnectionEvent)
meth public void closed(javax.mail.event.ConnectionEvent)
CLSS javax.mail.event.TransportListener
supr null
intf interface java.util.EventListener
meth public abstract void messageDelivered(javax.mail.event.TransportEvent)
meth public abstract void messageNotDelivered(javax.mail.event.TransportEvent)
meth public abstract void messagePartiallyDelivered(javax.mail.event.TransportEvent)
CLSS javax.mail.Session
supr class java.lang.Object
meth public static javax.mail.Session getInstance(java.util.Properties,javax.mail.Authenticator)
meth public static javax.mail.Session getDefaultInstance(java.util.Properties,javax.mail.Authenticator)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void setDebug(boolean)
meth public boolean getDebug()
meth public [Ljavax.mail.Provider; getProviders()
meth public javax.mail.Provider getProvider(java.lang.String) throws javax.mail.NoSuchProviderException
meth public void setProvider(javax.mail.Provider) throws javax.mail.NoSuchProviderException
meth public javax.mail.Store getStore() throws javax.mail.NoSuchProviderException
meth public javax.mail.Store getStore(java.lang.String) throws javax.mail.NoSuchProviderException
meth public javax.mail.Store getStore(javax.mail.URLName) throws javax.mail.NoSuchProviderException
meth public javax.mail.Store getStore(javax.mail.Provider) throws javax.mail.NoSuchProviderException
meth public javax.mail.Folder getFolder(javax.mail.URLName) throws javax.mail.MessagingException
meth public javax.mail.Transport getTransport() throws javax.mail.NoSuchProviderException
meth public javax.mail.Transport getTransport(java.lang.String) throws javax.mail.NoSuchProviderException
meth public javax.mail.Transport getTransport(javax.mail.URLName) throws javax.mail.NoSuchProviderException
meth public javax.mail.Transport getTransport(javax.mail.Provider) throws javax.mail.NoSuchProviderException
meth public javax.mail.Transport getTransport(javax.mail.Address) throws javax.mail.NoSuchProviderException
meth public void setPasswordAuthentication(javax.mail.URLName,javax.mail.PasswordAuthentication)
meth public javax.mail.PasswordAuthentication getPasswordAuthentication(javax.mail.URLName)
meth public javax.mail.PasswordAuthentication requestPasswordAuthentication(java.net.InetAddress,int,java.lang.String,java.lang.String,java.lang.String)
meth public java.util.Properties getProperties()
meth public java.lang.String getProperty(java.lang.String)
CLSS javax.mail.PasswordAuthentication
supr class java.lang.Object
cons public javax.mail.PasswordAuthentication(java.lang.String,java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getUserName()
meth public java.lang.String getPassword()
CLSS javax.mail.event.ConnectionListener
supr null
intf interface java.util.EventListener
meth public abstract void opened(javax.mail.event.ConnectionEvent)
meth public abstract void disconnected(javax.mail.event.ConnectionEvent)
meth public abstract void closed(javax.mail.event.ConnectionEvent)
CLSS javax.mail.IllegalWriteException
supr class javax.mail.MessagingException
cons public javax.mail.IllegalWriteException()
cons public javax.mail.IllegalWriteException(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
CLSS javax.mail.Provider$Type
supr class java.lang.Object
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
fld  public static final javax.mail.Provider$Type javax.mail.Provider$Type.STORE
fld  public static final javax.mail.Provider$Type javax.mail.Provider$Type.TRANSPORT
CLSS javax.mail.search.FromTerm
supr class javax.mail.search.AddressTerm
cons public javax.mail.search.FromTerm(javax.mail.Address)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public javax.mail.Address getAddress()
CLSS javax.mail.MessageRemovedException
supr class javax.mail.MessagingException
cons public javax.mail.MessageRemovedException()
cons public javax.mail.MessageRemovedException(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
CLSS javax.mail.event.StoreListener
supr null
intf interface java.util.EventListener
meth public abstract void notification(javax.mail.event.StoreEvent)
CLSS javax.mail.event.MessageCountListener
supr null
intf interface java.util.EventListener
meth public abstract void messagesAdded(javax.mail.event.MessageCountEvent)
meth public abstract void messagesRemoved(javax.mail.event.MessageCountEvent)
CLSS javax.mail.internet.MimeMessage$RecipientType
supr class javax.mail.Message$RecipientType
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
fld  public static final javax.mail.Message$RecipientType javax.mail.Message$RecipientType.TO
fld  public static final javax.mail.Message$RecipientType javax.mail.Message$RecipientType.CC
fld  public static final javax.mail.Message$RecipientType javax.mail.Message$RecipientType.BCC
fld  public static final javax.mail.internet.MimeMessage$RecipientType javax.mail.internet.MimeMessage$RecipientType.NEWSGROUPS
CLSS javax.mail.FetchProfile
supr class java.lang.Object
cons public javax.mail.FetchProfile()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void add(javax.mail.FetchProfile$Item)
meth public void add(java.lang.String)
meth public boolean contains(javax.mail.FetchProfile$Item)
meth public boolean contains(java.lang.String)
meth public [Ljavax.mail.FetchProfile$Item; getItems()
meth public [Ljava.lang.String; getHeaderNames()
CLSS javax.mail.search.MessageNumberTerm
supr class javax.mail.search.IntegerComparisonTerm
cons public javax.mail.search.MessageNumberTerm(int)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public int getNumber()
meth public int getComparison()
fld  public static final int javax.mail.search.ComparisonTerm.LE
fld  public static final int javax.mail.search.ComparisonTerm.LT
fld  public static final int javax.mail.search.ComparisonTerm.EQ
fld  public static final int javax.mail.search.ComparisonTerm.NE
fld  public static final int javax.mail.search.ComparisonTerm.GT
fld  public static final int javax.mail.search.ComparisonTerm.GE
CLSS javax.mail.search.SubjectTerm
supr class javax.mail.search.StringTerm
cons public javax.mail.search.SubjectTerm(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public java.lang.String getPattern()
meth public boolean getIgnoreCase()
CLSS javax.mail.internet.AddressException
supr class javax.mail.internet.ParseException
cons public javax.mail.internet.AddressException()
cons public javax.mail.internet.AddressException(java.lang.String)
cons public javax.mail.internet.AddressException(java.lang.String,java.lang.String)
cons public javax.mail.internet.AddressException(java.lang.String,java.lang.String,int)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
meth public java.lang.String getRef()
meth public int getPos()
CLSS javax.mail.event.MessageCountEvent
supr class javax.mail.event.MailEvent
cons public javax.mail.event.MessageCountEvent(javax.mail.Folder,int,boolean,[Ljavax.mail.Message;)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.Object getSource()
meth public void dispatch(java.lang.Object)
meth public int getType()
meth public boolean isRemoved()
meth public [Ljavax.mail.Message; getMessages()
fld  public static final int javax.mail.event.MessageCountEvent.ADDED
fld  public static final int javax.mail.event.MessageCountEvent.REMOVED
CLSS javax.mail.internet.MimeUtility
supr class java.lang.Object
meth public static java.lang.String getEncoding(javax.activation.DataSource)
meth public static java.io.InputStream decode(java.io.InputStream,java.lang.String) throws javax.mail.MessagingException
meth public static java.io.OutputStream encode(java.io.OutputStream,java.lang.String) throws javax.mail.MessagingException
meth public static java.lang.String encodeText(java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String encodeText(java.lang.String,java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String decodeText(java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String encodeWord(java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String encodeWord(java.lang.String,java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String decodeWord(java.lang.String) throws java.io.UnsupportedEncodingException, javax.mail.internet.ParseException
meth public static java.lang.String quote(java.lang.String,java.lang.String)
meth public static java.lang.String javaCharset(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
fld  public static final int javax.mail.internet.MimeUtility.ALL
CLSS javax.mail.Address
supr class java.lang.Object
cons public javax.mail.Address()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public abstract boolean equals(java.lang.Object)
meth public abstract java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract java.lang.String getType()
CLSS javax.mail.Authenticator
supr class java.lang.Object
cons public javax.mail.Authenticator()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
CLSS javax.mail.internet.InternetHeaders
supr class java.lang.Object
cons public javax.mail.internet.InternetHeaders()
cons public javax.mail.internet.InternetHeaders(java.io.InputStream) throws javax.mail.MessagingException
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void load(java.io.InputStream) throws javax.mail.MessagingException
meth public [Ljava.lang.String; getHeader(java.lang.String)
meth public java.lang.String getHeader(java.lang.String,java.lang.String)
meth public void setHeader(java.lang.String,java.lang.String)
meth public void addHeader(java.lang.String,java.lang.String)
meth public void removeHeader(java.lang.String)
meth public java.util.Enumeration getAllHeaders()
meth public java.util.Enumeration getMatchingHeaders([Ljava.lang.String;)
meth public java.util.Enumeration getNonMatchingHeaders([Ljava.lang.String;)
meth public void addHeaderLine(java.lang.String)
meth public java.util.Enumeration getAllHeaderLines()
meth public java.util.Enumeration getMatchingHeaderLines([Ljava.lang.String;)
meth public java.util.Enumeration getNonMatchingHeaderLines([Ljava.lang.String;)
CLSS javax.mail.internet.MimeMultipart
supr class javax.mail.Multipart
cons public javax.mail.internet.MimeMultipart()
cons public javax.mail.internet.MimeMultipart(java.lang.String)
cons public javax.mail.internet.MimeMultipart(javax.activation.DataSource) throws javax.mail.MessagingException
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getContentType()
meth public int getCount() throws javax.mail.MessagingException
meth public javax.mail.BodyPart getBodyPart(int) throws javax.mail.MessagingException
meth public boolean removeBodyPart(javax.mail.BodyPart) throws javax.mail.MessagingException
meth public void removeBodyPart(int) throws javax.mail.MessagingException
meth public void addBodyPart(javax.mail.BodyPart) throws javax.mail.MessagingException
meth public void addBodyPart(javax.mail.BodyPart,int) throws javax.mail.MessagingException
meth public void writeTo(java.io.OutputStream) throws java.io.IOException, javax.mail.MessagingException
meth public void setSubType(java.lang.String) throws javax.mail.MessagingException
meth public javax.mail.BodyPart getBodyPart(java.lang.String) throws javax.mail.MessagingException
CLSS javax.mail.internet.ParameterList
supr class java.lang.Object
cons public javax.mail.internet.ParameterList()
cons public javax.mail.internet.ParameterList(java.lang.String) throws javax.mail.internet.ParseException
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public int size()
meth public java.lang.String get(java.lang.String)
meth public void set(java.lang.String,java.lang.String)
meth public void remove(java.lang.String)
meth public java.util.Enumeration getNames()
CLSS javax.mail.MethodNotSupportedException
supr class javax.mail.MessagingException
cons public javax.mail.MethodNotSupportedException()
cons public javax.mail.MethodNotSupportedException(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
CLSS javax.mail.event.MessageChangedEvent
supr class javax.mail.event.MailEvent
cons public javax.mail.event.MessageChangedEvent(java.lang.Object,int,javax.mail.Message)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.Object getSource()
meth public void dispatch(java.lang.Object)
meth public int getMessageChangeType()
meth public javax.mail.Message getMessage()
fld  public static final int javax.mail.event.MessageChangedEvent.FLAGS_CHANGED
fld  public static final int javax.mail.event.MessageChangedEvent.ENVELOPE_CHANGED
CLSS javax.mail.internet.MimeBodyPart
supr class javax.mail.BodyPart
intf interface javax.mail.internet.MimePart
cons public javax.mail.internet.MimeBodyPart()
cons public javax.mail.internet.MimeBodyPart(java.io.InputStream) throws javax.mail.MessagingException
cons public javax.mail.internet.MimeBodyPart(javax.mail.internet.InternetHeaders,[B) throws javax.mail.MessagingException
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public int getSize() throws javax.mail.MessagingException
meth public int getLineCount() throws javax.mail.MessagingException
meth public java.lang.String getContentType() throws javax.mail.MessagingException
meth public boolean isMimeType(java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getDisposition() throws javax.mail.MessagingException
meth public void setDisposition(java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getDescription() throws javax.mail.MessagingException
meth public void setDescription(java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getFileName() throws javax.mail.MessagingException
meth public void setFileName(java.lang.String) throws javax.mail.MessagingException
meth public java.io.InputStream getInputStream() throws java.io.IOException, javax.mail.MessagingException
meth public javax.activation.DataHandler getDataHandler() throws javax.mail.MessagingException
meth public java.lang.Object getContent() throws java.io.IOException, javax.mail.MessagingException
meth public void setDataHandler(javax.activation.DataHandler) throws javax.mail.MessagingException
meth public void setContent(java.lang.Object,java.lang.String) throws javax.mail.MessagingException
meth public void setText(java.lang.String) throws javax.mail.MessagingException
meth public void setContent(javax.mail.Multipart) throws javax.mail.MessagingException
meth public void writeTo(java.io.OutputStream) throws java.io.IOException, javax.mail.MessagingException
meth public [Ljava.lang.String; getHeader(java.lang.String) throws javax.mail.MessagingException
meth public void setHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void addHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void removeHeader(java.lang.String) throws javax.mail.MessagingException
meth public java.util.Enumeration getAllHeaders() throws javax.mail.MessagingException
meth public java.util.Enumeration getMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
meth public java.util.Enumeration getNonMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
meth public java.lang.String getEncoding() throws javax.mail.MessagingException
meth public java.lang.String getContentID() throws javax.mail.MessagingException
meth public java.lang.String getContentMD5() throws javax.mail.MessagingException
meth public void setContentMD5(java.lang.String) throws javax.mail.MessagingException
meth public [Ljava.lang.String; getContentLanguage() throws javax.mail.MessagingException
meth public void setContentLanguage([Ljava.lang.String;) throws javax.mail.MessagingException
meth public void setDescription(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void setText(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void addHeaderLine(java.lang.String) throws javax.mail.MessagingException
meth public java.util.Enumeration getAllHeaderLines() throws javax.mail.MessagingException
meth public java.util.Enumeration getMatchingHeaderLines([Ljava.lang.String;) throws javax.mail.MessagingException
meth public java.util.Enumeration getNonMatchingHeaderLines([Ljava.lang.String;) throws javax.mail.MessagingException
fld  public static final java.lang.String javax.mail.Part.ATTACHMENT
fld  public static final java.lang.String javax.mail.Part.INLINE
CLSS javax.mail.Message$RecipientType
supr class java.lang.Object
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
fld  public static final javax.mail.Message$RecipientType javax.mail.Message$RecipientType.TO
fld  public static final javax.mail.Message$RecipientType javax.mail.Message$RecipientType.CC
fld  public static final javax.mail.Message$RecipientType javax.mail.Message$RecipientType.BCC
CLSS javax.mail.Header
supr class java.lang.Object
cons public javax.mail.Header(java.lang.String,java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getName()
meth public java.lang.String getValue()
CLSS javax.mail.internet.HeaderTokenizer
supr class java.lang.Object
cons public javax.mail.internet.HeaderTokenizer(java.lang.String,java.lang.String,boolean)
cons public javax.mail.internet.HeaderTokenizer(java.lang.String,java.lang.String)
cons public javax.mail.internet.HeaderTokenizer(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public javax.mail.internet.HeaderTokenizer$Token next() throws javax.mail.internet.ParseException
meth public javax.mail.internet.HeaderTokenizer$Token peek() throws javax.mail.internet.ParseException
meth public java.lang.String getRemainder()
fld  public static final java.lang.String javax.mail.internet.HeaderTokenizer.RFC822
fld  public static final java.lang.String javax.mail.internet.HeaderTokenizer.MIME
CLSS javax.mail.search.ReceivedDateTerm
supr class javax.mail.search.DateTerm
cons public javax.mail.search.ReceivedDateTerm(int,java.util.Date)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public java.util.Date getDate()
meth public int getComparison()
fld  public static final int javax.mail.search.ComparisonTerm.LE
fld  public static final int javax.mail.search.ComparisonTerm.LT
fld  public static final int javax.mail.search.ComparisonTerm.EQ
fld  public static final int javax.mail.search.ComparisonTerm.NE
fld  public static final int javax.mail.search.ComparisonTerm.GT
fld  public static final int javax.mail.search.ComparisonTerm.GE
CLSS javax.mail.URLName
supr class java.lang.Object
cons public javax.mail.URLName(java.lang.String,java.lang.String,int,java.lang.String,java.lang.String,java.lang.String)
cons public javax.mail.URLName(java.net.URL)
cons public javax.mail.URLName(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public int getPort()
meth public java.lang.String getProtocol()
meth public java.lang.String getFile()
meth public java.lang.String getHost()
meth public java.lang.String getUsername()
meth public java.lang.String getPassword()
meth public java.net.URL getURL() throws java.net.MalformedURLException
CLSS javax.mail.FolderClosedException
supr class javax.mail.MessagingException
cons public javax.mail.FolderClosedException(javax.mail.Folder)
cons public javax.mail.FolderClosedException(javax.mail.Folder,java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
meth public javax.mail.Folder getFolder()
CLSS javax.mail.event.ConnectionEvent
supr class javax.mail.event.MailEvent
cons public javax.mail.event.ConnectionEvent(java.lang.Object,int)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.Object getSource()
meth public void dispatch(java.lang.Object)
meth public int getType()
fld  public static final int javax.mail.event.ConnectionEvent.OPENED
fld  public static final int javax.mail.event.ConnectionEvent.DISCONNECTED
fld  public static final int javax.mail.event.ConnectionEvent.CLOSED
CLSS javax.mail.search.ComparisonTerm
supr class javax.mail.search.SearchTerm
cons public javax.mail.search.ComparisonTerm()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract boolean match(javax.mail.Message)
fld  public static final int javax.mail.search.ComparisonTerm.LE
fld  public static final int javax.mail.search.ComparisonTerm.LT
fld  public static final int javax.mail.search.ComparisonTerm.EQ
fld  public static final int javax.mail.search.ComparisonTerm.NE
fld  public static final int javax.mail.search.ComparisonTerm.GT
fld  public static final int javax.mail.search.ComparisonTerm.GE
CLSS javax.mail.MultipartDataSource
supr null
intf interface javax.activation.DataSource
meth public abstract java.io.InputStream getInputStream() throws java.io.IOException
meth public abstract java.io.OutputStream getOutputStream() throws java.io.IOException
meth public abstract java.lang.String getContentType()
meth public abstract java.lang.String getName()
meth public abstract int getCount()
meth public abstract javax.mail.BodyPart getBodyPart(int) throws javax.mail.MessagingException
CLSS javax.mail.search.FlagTerm
supr class javax.mail.search.SearchTerm
cons public javax.mail.search.FlagTerm(javax.mail.Flags,boolean)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public javax.mail.Flags getFlags()
meth public boolean getTestSet()
CLSS javax.mail.search.AndTerm
supr class javax.mail.search.SearchTerm
cons public javax.mail.search.AndTerm(javax.mail.search.SearchTerm,javax.mail.search.SearchTerm)
cons public javax.mail.search.AndTerm([Ljavax.mail.search.SearchTerm;)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public [Ljavax.mail.search.SearchTerm; getTerms()
CLSS javax.mail.Folder
supr class java.lang.Object
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract java.lang.String getName()
meth public abstract java.lang.String getFullName()
meth public javax.mail.Store getStore()
meth public abstract javax.mail.Folder getParent() throws javax.mail.MessagingException
meth public abstract boolean exists() throws javax.mail.MessagingException
meth public abstract [Ljavax.mail.Folder; list(java.lang.String) throws javax.mail.MessagingException
meth public [Ljavax.mail.Folder; listSubscribed(java.lang.String) throws javax.mail.MessagingException
meth public [Ljavax.mail.Folder; list() throws javax.mail.MessagingException
meth public [Ljavax.mail.Folder; listSubscribed() throws javax.mail.MessagingException
meth public abstract char getSeparator() throws javax.mail.MessagingException
meth public abstract int getType() throws javax.mail.MessagingException
meth public abstract boolean create(int) throws javax.mail.MessagingException
meth public boolean isSubscribed()
meth public void setSubscribed(boolean) throws javax.mail.MessagingException
meth public abstract boolean hasNewMessages() throws javax.mail.MessagingException
meth public abstract javax.mail.Folder getFolder(java.lang.String) throws javax.mail.MessagingException
meth public abstract boolean delete(boolean) throws javax.mail.MessagingException
meth public abstract boolean renameTo(javax.mail.Folder) throws javax.mail.MessagingException
meth public abstract void open(int) throws javax.mail.MessagingException
meth public abstract void close(boolean) throws javax.mail.MessagingException
meth public abstract boolean isOpen()
meth public abstract javax.mail.Flags getPermanentFlags()
meth public abstract int getMessageCount() throws javax.mail.MessagingException
meth public int getNewMessageCount() throws javax.mail.MessagingException
meth public int getUnreadMessageCount() throws javax.mail.MessagingException
meth public abstract javax.mail.Message getMessage(int) throws javax.mail.MessagingException
meth public [Ljavax.mail.Message; getMessages(int,int) throws javax.mail.MessagingException
meth public [Ljavax.mail.Message; getMessages([I) throws javax.mail.MessagingException
meth public [Ljavax.mail.Message; getMessages() throws javax.mail.MessagingException
meth public abstract void appendMessages([Ljavax.mail.Message;) throws javax.mail.MessagingException
meth public void fetch([Ljavax.mail.Message;,javax.mail.FetchProfile) throws javax.mail.MessagingException
meth public void setFlags([Ljavax.mail.Message;,javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public void setFlags(int,int,javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public void setFlags([I,javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public void copyMessages([Ljavax.mail.Message;,javax.mail.Folder) throws javax.mail.MessagingException
meth public abstract [Ljavax.mail.Message; expunge() throws javax.mail.MessagingException
meth public [Ljavax.mail.Message; search(javax.mail.search.SearchTerm) throws javax.mail.MessagingException
meth public [Ljavax.mail.Message; search(javax.mail.search.SearchTerm,[Ljavax.mail.Message;) throws javax.mail.MessagingException
meth public void addConnectionListener(javax.mail.event.ConnectionListener)
meth public void removeConnectionListener(javax.mail.event.ConnectionListener)
meth public void addFolderListener(javax.mail.event.FolderListener)
meth public void removeFolderListener(javax.mail.event.FolderListener)
meth public void addMessageCountListener(javax.mail.event.MessageCountListener)
meth public void removeMessageCountListener(javax.mail.event.MessageCountListener)
meth public void addMessageChangedListener(javax.mail.event.MessageChangedListener)
meth public void removeMessageChangedListener(javax.mail.event.MessageChangedListener)
fld  public static final int javax.mail.Folder.HOLDS_MESSAGES
fld  public static final int javax.mail.Folder.HOLDS_FOLDERS
fld  public static final int javax.mail.Folder.READ_ONLY
fld  public static final int javax.mail.Folder.READ_WRITE
CLSS javax.mail.event.MailEvent
supr class java.util.EventObject
cons public javax.mail.event.MailEvent(java.lang.Object)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.Object getSource()
meth public abstract void dispatch(java.lang.Object)
CLSS javax.mail.Multipart
supr class java.lang.Object
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getContentType()
meth public int getCount() throws javax.mail.MessagingException
meth public javax.mail.BodyPart getBodyPart(int) throws javax.mail.MessagingException
meth public boolean removeBodyPart(javax.mail.BodyPart) throws javax.mail.MessagingException
meth public void removeBodyPart(int) throws javax.mail.MessagingException
meth public void addBodyPart(javax.mail.BodyPart) throws javax.mail.MessagingException
meth public void addBodyPart(javax.mail.BodyPart,int) throws javax.mail.MessagingException
meth public abstract void writeTo(java.io.OutputStream) throws java.io.IOException, javax.mail.MessagingException
CLSS javax.mail.Part
supr null
meth public abstract int getSize() throws javax.mail.MessagingException
meth public abstract int getLineCount() throws javax.mail.MessagingException
meth public abstract java.lang.String getContentType() throws javax.mail.MessagingException
meth public abstract boolean isMimeType(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getDisposition() throws javax.mail.MessagingException
meth public abstract void setDisposition(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getDescription() throws javax.mail.MessagingException
meth public abstract void setDescription(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getFileName() throws javax.mail.MessagingException
meth public abstract void setFileName(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.io.InputStream getInputStream() throws java.io.IOException, javax.mail.MessagingException
meth public abstract javax.activation.DataHandler getDataHandler() throws javax.mail.MessagingException
meth public abstract java.lang.Object getContent() throws java.io.IOException, javax.mail.MessagingException
meth public abstract void setDataHandler(javax.activation.DataHandler) throws javax.mail.MessagingException
meth public abstract void setContent(java.lang.Object,java.lang.String) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setContent(javax.mail.Multipart) throws javax.mail.MessagingException
meth public abstract void writeTo(java.io.OutputStream) throws java.io.IOException, javax.mail.MessagingException
meth public abstract [Ljava.lang.String; getHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void addHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void removeHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getAllHeaders() throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getNonMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
fld  public static final java.lang.String javax.mail.Part.ATTACHMENT
fld  public static final java.lang.String javax.mail.Part.INLINE
CLSS javax.mail.FetchProfile$Item
supr class java.lang.Object
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
fld  public static final javax.mail.FetchProfile$Item javax.mail.FetchProfile$Item.ENVELOPE
fld  public static final javax.mail.FetchProfile$Item javax.mail.FetchProfile$Item.CONTENT_INFO
fld  public static final javax.mail.FetchProfile$Item javax.mail.FetchProfile$Item.FLAGS
CLSS javax.mail.Flags$Flag
supr class java.lang.Object
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
fld  public static final javax.mail.Flags$Flag javax.mail.Flags$Flag.ANSWERED
fld  public static final javax.mail.Flags$Flag javax.mail.Flags$Flag.DELETED
fld  public static final javax.mail.Flags$Flag javax.mail.Flags$Flag.DRAFT
fld  public static final javax.mail.Flags$Flag javax.mail.Flags$Flag.FLAGGED
fld  public static final javax.mail.Flags$Flag javax.mail.Flags$Flag.RECENT
fld  public static final javax.mail.Flags$Flag javax.mail.Flags$Flag.SEEN
fld  public static final javax.mail.Flags$Flag javax.mail.Flags$Flag.USER
CLSS javax.mail.search.SizeTerm
supr class javax.mail.search.IntegerComparisonTerm
cons public javax.mail.search.SizeTerm(int,int)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public int getNumber()
meth public int getComparison()
fld  public static final int javax.mail.search.ComparisonTerm.LE
fld  public static final int javax.mail.search.ComparisonTerm.LT
fld  public static final int javax.mail.search.ComparisonTerm.EQ
fld  public static final int javax.mail.search.ComparisonTerm.NE
fld  public static final int javax.mail.search.ComparisonTerm.GT
fld  public static final int javax.mail.search.ComparisonTerm.GE
CLSS javax.mail.UIDFolder$FetchProfileItem
supr class javax.mail.FetchProfile$Item
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
fld  public static final javax.mail.FetchProfile$Item javax.mail.FetchProfile$Item.ENVELOPE
fld  public static final javax.mail.FetchProfile$Item javax.mail.FetchProfile$Item.CONTENT_INFO
fld  public static final javax.mail.FetchProfile$Item javax.mail.FetchProfile$Item.FLAGS
fld  public static final javax.mail.UIDFolder$FetchProfileItem javax.mail.UIDFolder$FetchProfileItem.UID
CLSS javax.mail.Flags
supr class java.lang.Object
intf interface java.lang.Cloneable
cons public javax.mail.Flags()
cons public javax.mail.Flags(javax.mail.Flags)
cons public javax.mail.Flags(javax.mail.Flags$Flag)
cons public javax.mail.Flags(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.Object clone()
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void add(javax.mail.Flags$Flag)
meth public void add(java.lang.String)
meth public void add(javax.mail.Flags)
meth public void remove(javax.mail.Flags$Flag)
meth public void remove(java.lang.String)
meth public void remove(javax.mail.Flags)
meth public boolean contains(javax.mail.Flags$Flag)
meth public boolean contains(java.lang.String)
meth public boolean contains(javax.mail.Flags)
meth public [Ljavax.mail.Flags$Flag; getSystemFlags()
meth public [Ljava.lang.String; getUserFlags()
CLSS javax.mail.internet.InternetAddress
supr class javax.mail.Address
cons public javax.mail.internet.InternetAddress()
cons public javax.mail.internet.InternetAddress(java.lang.String) throws javax.mail.internet.AddressException
cons public javax.mail.internet.InternetAddress(java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
cons public javax.mail.internet.InternetAddress(java.lang.String,java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String toString([Ljavax.mail.Address;)
meth public static javax.mail.internet.InternetAddress getLocalAddress(javax.mail.Session)
meth public static [Ljavax.mail.internet.InternetAddress; parse(java.lang.String) throws javax.mail.internet.AddressException
meth public static [Ljavax.mail.internet.InternetAddress; parse(java.lang.String,boolean) throws javax.mail.internet.AddressException
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getType()
meth public void setAddress(java.lang.String)
meth public void setPersonal(java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
meth public void setPersonal(java.lang.String) throws java.io.UnsupportedEncodingException
meth public java.lang.String getAddress()
meth public java.lang.String getPersonal()
CLSS javax.mail.search.IntegerComparisonTerm
supr class javax.mail.search.ComparisonTerm
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract boolean match(javax.mail.Message)
meth public int getNumber()
meth public int getComparison()
fld  public static final int javax.mail.search.ComparisonTerm.LE
fld  public static final int javax.mail.search.ComparisonTerm.LT
fld  public static final int javax.mail.search.ComparisonTerm.EQ
fld  public static final int javax.mail.search.ComparisonTerm.NE
fld  public static final int javax.mail.search.ComparisonTerm.GT
fld  public static final int javax.mail.search.ComparisonTerm.GE
CLSS javax.mail.Transport
supr class java.lang.Object
cons public javax.mail.Transport(javax.mail.Session,javax.mail.URLName)
meth public static void send(javax.mail.Message) throws javax.mail.MessagingException
meth public static void send(javax.mail.Message,[Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void connect(java.lang.String,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void connect(java.lang.String,int,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void connect() throws javax.mail.MessagingException
meth public boolean isConnected()
meth public void close() throws javax.mail.MessagingException
meth public javax.mail.URLName getURLName()
meth public abstract void sendMessage(javax.mail.Message,[Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public void addConnectionListener(javax.mail.event.ConnectionListener)
meth public void removeConnectionListener(javax.mail.event.ConnectionListener)
meth public void addTransportListener(javax.mail.event.TransportListener)
meth public void removeTransportListener(javax.mail.event.TransportListener)
CLSS javax.mail.event.TransportEvent
supr class javax.mail.event.MailEvent
cons public javax.mail.event.TransportEvent(javax.mail.Transport,int,[Ljavax.mail.Address;,[Ljavax.mail.Address;,[Ljavax.mail.Address;,javax.mail.Message)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.Object getSource()
meth public void dispatch(java.lang.Object)
meth public int getType()
meth public [Ljavax.mail.Address; getValidSentAddresses()
meth public [Ljavax.mail.Address; getValidUnsentAddresses()
meth public [Ljavax.mail.Address; getInvalidAddresses()
fld  public static final int javax.mail.event.TransportEvent.MESSAGE_DELIVERED
fld  public static final int javax.mail.event.TransportEvent.MESSAGE_NOT_DELIVERED
fld  public static final int javax.mail.event.TransportEvent.MESSAGE_PARTIALLY_DELIVERED
CLSS javax.mail.internet.MimePart
supr null
intf interface javax.mail.Part
meth public abstract int getSize() throws javax.mail.MessagingException
meth public abstract int getLineCount() throws javax.mail.MessagingException
meth public abstract java.lang.String getContentType() throws javax.mail.MessagingException
meth public abstract boolean isMimeType(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getDisposition() throws javax.mail.MessagingException
meth public abstract void setDisposition(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getDescription() throws javax.mail.MessagingException
meth public abstract void setDescription(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getFileName() throws javax.mail.MessagingException
meth public abstract void setFileName(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.io.InputStream getInputStream() throws java.io.IOException, javax.mail.MessagingException
meth public abstract javax.activation.DataHandler getDataHandler() throws javax.mail.MessagingException
meth public abstract java.lang.Object getContent() throws java.io.IOException, javax.mail.MessagingException
meth public abstract void setDataHandler(javax.activation.DataHandler) throws javax.mail.MessagingException
meth public abstract void setContent(java.lang.Object,java.lang.String) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setContent(javax.mail.Multipart) throws javax.mail.MessagingException
meth public abstract void writeTo(java.io.OutputStream) throws java.io.IOException, javax.mail.MessagingException
meth public abstract [Ljava.lang.String; getHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void addHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void removeHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getAllHeaders() throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getNonMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
meth public abstract java.lang.String getHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void addHeaderLine(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getAllHeaderLines() throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getMatchingHeaderLines([Ljava.lang.String;) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getNonMatchingHeaderLines([Ljava.lang.String;) throws javax.mail.MessagingException
meth public abstract java.lang.String getEncoding() throws javax.mail.MessagingException
meth public abstract java.lang.String getContentID() throws javax.mail.MessagingException
meth public abstract java.lang.String getContentMD5() throws javax.mail.MessagingException
meth public abstract void setContentMD5(java.lang.String) throws javax.mail.MessagingException
meth public abstract [Ljava.lang.String; getContentLanguage() throws javax.mail.MessagingException
meth public abstract void setContentLanguage([Ljava.lang.String;) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String,java.lang.String) throws javax.mail.MessagingException
fld  public static final java.lang.String javax.mail.Part.ATTACHMENT
fld  public static final java.lang.String javax.mail.Part.INLINE
CLSS javax.mail.BodyPart
supr class java.lang.Object
intf interface javax.mail.Part
cons public javax.mail.BodyPart()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract int getSize() throws javax.mail.MessagingException
meth public abstract int getLineCount() throws javax.mail.MessagingException
meth public abstract java.lang.String getContentType() throws javax.mail.MessagingException
meth public abstract boolean isMimeType(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getDisposition() throws javax.mail.MessagingException
meth public abstract void setDisposition(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getDescription() throws javax.mail.MessagingException
meth public abstract void setDescription(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getFileName() throws javax.mail.MessagingException
meth public abstract void setFileName(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.io.InputStream getInputStream() throws java.io.IOException, javax.mail.MessagingException
meth public abstract javax.activation.DataHandler getDataHandler() throws javax.mail.MessagingException
meth public abstract java.lang.Object getContent() throws java.io.IOException, javax.mail.MessagingException
meth public abstract void setDataHandler(javax.activation.DataHandler) throws javax.mail.MessagingException
meth public abstract void setContent(java.lang.Object,java.lang.String) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setContent(javax.mail.Multipart) throws javax.mail.MessagingException
meth public abstract void writeTo(java.io.OutputStream) throws java.io.IOException, javax.mail.MessagingException
meth public abstract [Ljava.lang.String; getHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void addHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void removeHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getAllHeaders() throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getNonMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
fld  public static final java.lang.String javax.mail.Part.ATTACHMENT
fld  public static final java.lang.String javax.mail.Part.INLINE
CLSS javax.mail.event.StoreEvent
supr class javax.mail.event.MailEvent
cons public javax.mail.event.StoreEvent(javax.mail.Store,int,java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.Object getSource()
meth public void dispatch(java.lang.Object)
meth public int getMessageType()
meth public java.lang.String getMessage()
fld  public static final int javax.mail.event.StoreEvent.ALERT
fld  public static final int javax.mail.event.StoreEvent.NOTICE
CLSS javax.mail.event.FolderEvent
supr class javax.mail.event.MailEvent
cons public javax.mail.event.FolderEvent(java.lang.Object,javax.mail.Folder,int)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.Object getSource()
meth public void dispatch(java.lang.Object)
meth public int getType()
meth public javax.mail.Folder getFolder()
fld  public static final int javax.mail.event.FolderEvent.CREATED
fld  public static final int javax.mail.event.FolderEvent.DELETED
fld  public static final int javax.mail.event.FolderEvent.RENAMED
CLSS javax.mail.UIDFolder
supr null
meth public abstract long getUIDValidity() throws javax.mail.MessagingException
meth public abstract javax.mail.Message getMessageByUID(long) throws javax.mail.MessagingException
meth public abstract [Ljavax.mail.Message; getMessagesByUID(long,long) throws javax.mail.MessagingException
meth public abstract [Ljavax.mail.Message; getMessagesByUID([J) throws javax.mail.MessagingException
meth public abstract long getUID(javax.mail.Message) throws javax.mail.MessagingException
fld  public static final long javax.mail.UIDFolder.LASTUID
CLSS javax.mail.internet.NewsAddress
supr class javax.mail.Address
cons public javax.mail.internet.NewsAddress()
cons public javax.mail.internet.NewsAddress(java.lang.String)
cons public javax.mail.internet.NewsAddress(java.lang.String,java.lang.String)
meth public static java.lang.String toString([Ljavax.mail.Address;)
meth public static [Ljavax.mail.internet.NewsAddress; parse(java.lang.String) throws javax.mail.internet.AddressException
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getType()
meth public void setNewsgroup(java.lang.String)
meth public java.lang.String getNewsgroup()
meth public void setHost(java.lang.String)
meth public java.lang.String getHost()
CLSS javax.mail.search.BodyTerm
supr class javax.mail.search.StringTerm
cons public javax.mail.search.BodyTerm(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public java.lang.String getPattern()
meth public boolean getIgnoreCase()
CLSS javax.mail.search.OrTerm
supr class javax.mail.search.SearchTerm
cons public javax.mail.search.OrTerm(javax.mail.search.SearchTerm,javax.mail.search.SearchTerm)
cons public javax.mail.search.OrTerm([Ljavax.mail.search.SearchTerm;)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public [Ljavax.mail.search.SearchTerm; getTerms()
CLSS javax.mail.FolderNotFoundException
supr class javax.mail.MessagingException
cons public javax.mail.FolderNotFoundException()
cons public javax.mail.FolderNotFoundException(java.lang.String,javax.mail.Folder)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
meth public javax.mail.Folder getFolder()
CLSS javax.mail.NoSuchProviderException
supr class javax.mail.MessagingException
cons public javax.mail.NoSuchProviderException()
cons public javax.mail.NoSuchProviderException(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
CLSS javax.mail.search.HeaderTerm
supr class javax.mail.search.StringTerm
cons public javax.mail.search.HeaderTerm(java.lang.String,java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public java.lang.String getPattern()
meth public boolean getIgnoreCase()
meth public java.lang.String getHeaderName()
CLSS javax.mail.search.SearchTerm
supr class java.lang.Object
cons public javax.mail.search.SearchTerm()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract boolean match(javax.mail.Message)
CLSS javax.mail.internet.ParseException
supr class javax.mail.MessagingException
cons public javax.mail.internet.ParseException()
cons public javax.mail.internet.ParseException(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
CLSS javax.mail.search.SentDateTerm
supr class javax.mail.search.DateTerm
cons public javax.mail.search.SentDateTerm(int,java.util.Date)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public java.util.Date getDate()
meth public int getComparison()
fld  public static final int javax.mail.search.ComparisonTerm.LE
fld  public static final int javax.mail.search.ComparisonTerm.LT
fld  public static final int javax.mail.search.ComparisonTerm.EQ
fld  public static final int javax.mail.search.ComparisonTerm.NE
fld  public static final int javax.mail.search.ComparisonTerm.GT
fld  public static final int javax.mail.search.ComparisonTerm.GE
CLSS javax.mail.search.StringTerm
supr class javax.mail.search.SearchTerm
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract boolean match(javax.mail.Message)
meth public java.lang.String getPattern()
meth public boolean getIgnoreCase()
CLSS javax.mail.internet.HeaderTokenizer$Token
supr class java.lang.Object
cons public javax.mail.internet.HeaderTokenizer$Token(int,java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public int getType()
meth public java.lang.String getValue()
fld  public static final int javax.mail.internet.HeaderTokenizer$Token.ATOM
fld  public static final int javax.mail.internet.HeaderTokenizer$Token.QUOTEDSTRING
fld  public static final int javax.mail.internet.HeaderTokenizer$Token.COMMENT
fld  public static final int javax.mail.internet.HeaderTokenizer$Token.EOF
CLSS javax.mail.event.FolderListener
supr null
intf interface java.util.EventListener
meth public abstract void folderCreated(javax.mail.event.FolderEvent)
meth public abstract void folderDeleted(javax.mail.event.FolderEvent)
meth public abstract void folderRenamed(javax.mail.event.FolderEvent)
CLSS javax.mail.search.NotTerm
supr class javax.mail.search.SearchTerm
cons public javax.mail.search.NotTerm(javax.mail.search.SearchTerm)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public javax.mail.search.SearchTerm getTerm()
CLSS javax.mail.search.SearchException
supr class javax.mail.MessagingException
cons public javax.mail.search.SearchException()
cons public javax.mail.search.SearchException(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
CLSS javax.mail.search.MessageIDTerm
supr class javax.mail.search.StringTerm
cons public javax.mail.search.MessageIDTerm(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public java.lang.String getPattern()
meth public boolean getIgnoreCase()
CLSS javax.mail.search.RecipientTerm
supr class javax.mail.search.AddressTerm
cons public javax.mail.search.RecipientTerm(javax.mail.Message$RecipientType,javax.mail.Address)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public boolean match(javax.mail.Message)
meth public javax.mail.Address getAddress()
meth public javax.mail.Message$RecipientType getRecipientType()
CLSS javax.mail.event.MessageCountAdapter
supr class java.lang.Object
intf interface javax.mail.event.MessageCountListener
cons public javax.mail.event.MessageCountAdapter()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void messagesAdded(javax.mail.event.MessageCountEvent)
meth public void messagesRemoved(javax.mail.event.MessageCountEvent)
CLSS javax.mail.AuthenticationFailedException
supr class javax.mail.MessagingException
cons public javax.mail.AuthenticationFailedException()
cons public javax.mail.AuthenticationFailedException(java.lang.String)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
CLSS javax.mail.SendFailedException
supr class javax.mail.MessagingException
cons public javax.mail.SendFailedException()
cons public javax.mail.SendFailedException(java.lang.String)
cons public javax.mail.SendFailedException(java.lang.String,java.lang.Exception)
cons public javax.mail.SendFailedException(java.lang.String,java.lang.Exception,[Ljavax.mail.Address;,[Ljavax.mail.Address;,[Ljavax.mail.Address;)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
meth public [Ljavax.mail.Address; getValidSentAddresses()
meth public [Ljavax.mail.Address; getValidUnsentAddresses()
meth public [Ljavax.mail.Address; getInvalidAddresses()
CLSS javax.mail.Provider
supr class java.lang.Object
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public javax.mail.Provider$Type getType()
meth public java.lang.String getProtocol()
meth public java.lang.String getClassName()
meth public java.lang.String getVendor()
meth public java.lang.String getVersion()
CLSS javax.mail.internet.MimePartDataSource
supr class java.lang.Object
intf interface javax.activation.DataSource
cons public javax.mail.internet.MimePartDataSource(javax.mail.internet.MimePart)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.io.InputStream getInputStream() throws java.io.IOException
meth public java.io.OutputStream getOutputStream() throws java.io.IOException
meth public java.lang.String getContentType()
meth public java.lang.String getName()
CLSS javax.mail.internet.ContentType
supr class java.lang.Object
cons public javax.mail.internet.ContentType()
cons public javax.mail.internet.ContentType(java.lang.String,java.lang.String,javax.mail.internet.ParameterList)
cons public javax.mail.internet.ContentType(java.lang.String) throws javax.mail.internet.ParseException
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getPrimaryType()
meth public java.lang.String getSubType()
meth public java.lang.String getBaseType()
meth public java.lang.String getParameter(java.lang.String)
meth public javax.mail.internet.ParameterList getParameterList()
meth public void setPrimaryType(java.lang.String)
meth public void setSubType(java.lang.String)
meth public void setParameter(java.lang.String,java.lang.String)
meth public void setParameterList(javax.mail.internet.ParameterList)
meth public boolean match(javax.mail.internet.ContentType)
meth public boolean match(java.lang.String)
CLSS javax.mail.MessagingException
supr class java.lang.Exception
cons public javax.mail.MessagingException()
cons public javax.mail.MessagingException(java.lang.String)
cons public javax.mail.MessagingException(java.lang.String,java.lang.Exception)
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public java.lang.String getMessage()
meth public java.lang.String getLocalizedMessage()
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Exception getNextException()
meth public boolean setNextException(java.lang.Exception)
CLSS javax.mail.Message
supr class java.lang.Object
intf interface javax.mail.Part
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract [Ljavax.mail.Address; getFrom() throws javax.mail.MessagingException
meth public abstract void setFrom() throws javax.mail.MessagingException
meth public abstract void setFrom(javax.mail.Address) throws javax.mail.MessagingException
meth public abstract void addFrom([Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public abstract [Ljavax.mail.Address; getRecipients(javax.mail.Message$RecipientType) throws javax.mail.MessagingException
meth public [Ljavax.mail.Address; getAllRecipients() throws javax.mail.MessagingException
meth public abstract void setRecipients(javax.mail.Message$RecipientType,[Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public void setRecipient(javax.mail.Message$RecipientType,javax.mail.Address) throws javax.mail.MessagingException
meth public abstract void addRecipients(javax.mail.Message$RecipientType,[Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public void addRecipient(javax.mail.Message$RecipientType,javax.mail.Address) throws javax.mail.MessagingException
meth public [Ljavax.mail.Address; getReplyTo() throws javax.mail.MessagingException
meth public void setReplyTo([Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public abstract java.lang.String getSubject() throws javax.mail.MessagingException
meth public abstract void setSubject(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.util.Date getSentDate() throws javax.mail.MessagingException
meth public abstract void setSentDate(java.util.Date) throws javax.mail.MessagingException
meth public abstract java.util.Date getReceivedDate() throws javax.mail.MessagingException
meth public abstract javax.mail.Flags getFlags() throws javax.mail.MessagingException
meth public boolean isSet(javax.mail.Flags$Flag) throws javax.mail.MessagingException
meth public abstract void setFlags(javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public void setFlag(javax.mail.Flags$Flag,boolean) throws javax.mail.MessagingException
meth public int getMessageNumber()
meth public javax.mail.Folder getFolder()
meth public boolean isExpunged()
meth public abstract javax.mail.Message reply(boolean) throws javax.mail.MessagingException
meth public abstract void saveChanges() throws javax.mail.MessagingException
meth public boolean match(javax.mail.search.SearchTerm) throws javax.mail.MessagingException
meth public abstract int getSize() throws javax.mail.MessagingException
meth public abstract int getLineCount() throws javax.mail.MessagingException
meth public abstract java.lang.String getContentType() throws javax.mail.MessagingException
meth public abstract boolean isMimeType(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getDisposition() throws javax.mail.MessagingException
meth public abstract void setDisposition(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getDescription() throws javax.mail.MessagingException
meth public abstract void setDescription(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String getFileName() throws javax.mail.MessagingException
meth public abstract void setFileName(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.io.InputStream getInputStream() throws java.io.IOException, javax.mail.MessagingException
meth public abstract javax.activation.DataHandler getDataHandler() throws javax.mail.MessagingException
meth public abstract java.lang.Object getContent() throws java.io.IOException, javax.mail.MessagingException
meth public abstract void setDataHandler(javax.activation.DataHandler) throws javax.mail.MessagingException
meth public abstract void setContent(java.lang.Object,java.lang.String) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setContent(javax.mail.Multipart) throws javax.mail.MessagingException
meth public abstract void writeTo(java.io.OutputStream) throws java.io.IOException, javax.mail.MessagingException
meth public abstract [Ljava.lang.String; getHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void addHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void removeHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getAllHeaders() throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getNonMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
fld  public static final java.lang.String javax.mail.Part.ATTACHMENT
fld  public static final java.lang.String javax.mail.Part.INLINE
CLSS javax.mail.search.AddressTerm
supr class javax.mail.search.SearchTerm
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract boolean match(javax.mail.Message)
meth public javax.mail.Address getAddress()
CLSS javax.mail.search.DateTerm
supr class javax.mail.search.ComparisonTerm
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public abstract boolean match(javax.mail.Message)
meth public java.util.Date getDate()
meth public int getComparison()
fld  public static final int javax.mail.search.ComparisonTerm.LE
fld  public static final int javax.mail.search.ComparisonTerm.LT
fld  public static final int javax.mail.search.ComparisonTerm.EQ
fld  public static final int javax.mail.search.ComparisonTerm.NE
fld  public static final int javax.mail.search.ComparisonTerm.GT
fld  public static final int javax.mail.search.ComparisonTerm.GE
CLSS javax.mail.event.TransportAdapter
supr class java.lang.Object
intf interface javax.mail.event.TransportListener
cons public javax.mail.event.TransportAdapter()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void messageDelivered(javax.mail.event.TransportEvent)
meth public void messageNotDelivered(javax.mail.event.TransportEvent)
meth public void messagePartiallyDelivered(javax.mail.event.TransportEvent)
CLSS javax.mail.Store
supr class java.lang.Object
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void connect() throws javax.mail.MessagingException
meth public void connect(java.lang.String,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void connect(java.lang.String,int,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public boolean isConnected()
meth public void close() throws javax.mail.MessagingException
meth public javax.mail.URLName getURLName()
meth public abstract javax.mail.Folder getDefaultFolder() throws javax.mail.MessagingException
meth public abstract javax.mail.Folder getFolder(java.lang.String) throws javax.mail.MessagingException
meth public abstract javax.mail.Folder getFolder(javax.mail.URLName) throws javax.mail.MessagingException
meth public void addConnectionListener(javax.mail.event.ConnectionListener)
meth public void removeConnectionListener(javax.mail.event.ConnectionListener)
meth public void addStoreListener(javax.mail.event.StoreListener)
meth public void removeStoreListener(javax.mail.event.StoreListener)
meth public void addFolderListener(javax.mail.event.FolderListener)
meth public void removeFolderListener(javax.mail.event.FolderListener)
CLSS javax.mail.internet.MimeMessage
supr class javax.mail.Message
intf interface javax.mail.internet.MimePart
cons public javax.mail.internet.MimeMessage(javax.mail.Session)
cons public javax.mail.internet.MimeMessage(javax.mail.Session,java.io.InputStream) throws javax.mail.MessagingException
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public [Ljavax.mail.Address; getFrom() throws javax.mail.MessagingException
meth public void setFrom() throws javax.mail.MessagingException
meth public void setFrom(javax.mail.Address) throws javax.mail.MessagingException
meth public void addFrom([Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public [Ljavax.mail.Address; getRecipients(javax.mail.Message$RecipientType) throws javax.mail.MessagingException
meth public [Ljavax.mail.Address; getAllRecipients() throws javax.mail.MessagingException
meth public void setRecipients(javax.mail.Message$RecipientType,[Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public void setRecipient(javax.mail.Message$RecipientType,javax.mail.Address) throws javax.mail.MessagingException
meth public void addRecipients(javax.mail.Message$RecipientType,[Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public void addRecipient(javax.mail.Message$RecipientType,javax.mail.Address) throws javax.mail.MessagingException
meth public [Ljavax.mail.Address; getReplyTo() throws javax.mail.MessagingException
meth public void setReplyTo([Ljavax.mail.Address;) throws javax.mail.MessagingException
meth public java.lang.String getSubject() throws javax.mail.MessagingException
meth public void setSubject(java.lang.String) throws javax.mail.MessagingException
meth public java.util.Date getSentDate() throws javax.mail.MessagingException
meth public void setSentDate(java.util.Date) throws javax.mail.MessagingException
meth public java.util.Date getReceivedDate() throws javax.mail.MessagingException
meth public javax.mail.Flags getFlags() throws javax.mail.MessagingException
meth public boolean isSet(javax.mail.Flags$Flag) throws javax.mail.MessagingException
meth public void setFlags(javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public void setFlag(javax.mail.Flags$Flag,boolean) throws javax.mail.MessagingException
meth public int getMessageNumber()
meth public javax.mail.Folder getFolder()
meth public boolean isExpunged()
meth public javax.mail.Message reply(boolean) throws javax.mail.MessagingException
meth public void saveChanges() throws javax.mail.MessagingException
meth public boolean match(javax.mail.search.SearchTerm) throws javax.mail.MessagingException
meth public int getSize() throws javax.mail.MessagingException
meth public int getLineCount() throws javax.mail.MessagingException
meth public java.lang.String getContentType() throws javax.mail.MessagingException
meth public boolean isMimeType(java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getDisposition() throws javax.mail.MessagingException
meth public void setDisposition(java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getDescription() throws javax.mail.MessagingException
meth public void setDescription(java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getFileName() throws javax.mail.MessagingException
meth public void setFileName(java.lang.String) throws javax.mail.MessagingException
meth public java.io.InputStream getInputStream() throws java.io.IOException, javax.mail.MessagingException
meth public javax.activation.DataHandler getDataHandler() throws javax.mail.MessagingException
meth public java.lang.Object getContent() throws java.io.IOException, javax.mail.MessagingException
meth public void setDataHandler(javax.activation.DataHandler) throws javax.mail.MessagingException
meth public void setContent(java.lang.Object,java.lang.String) throws javax.mail.MessagingException
meth public void setText(java.lang.String) throws javax.mail.MessagingException
meth public void setContent(javax.mail.Multipart) throws javax.mail.MessagingException
meth public void writeTo(java.io.OutputStream) throws java.io.IOException, javax.mail.MessagingException
meth public [Ljava.lang.String; getHeader(java.lang.String) throws javax.mail.MessagingException
meth public void setHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void addHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void removeHeader(java.lang.String) throws javax.mail.MessagingException
meth public java.util.Enumeration getAllHeaders() throws javax.mail.MessagingException
meth public java.util.Enumeration getMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
meth public java.util.Enumeration getNonMatchingHeaders([Ljava.lang.String;) throws javax.mail.MessagingException
meth public void setSubject(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getEncoding() throws javax.mail.MessagingException
meth public java.lang.String getContentID() throws javax.mail.MessagingException
meth public void setContentID(java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getContentMD5() throws javax.mail.MessagingException
meth public void setContentMD5(java.lang.String) throws javax.mail.MessagingException
meth public void setDescription(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public [Ljava.lang.String; getContentLanguage() throws javax.mail.MessagingException
meth public void setContentLanguage([Ljava.lang.String;) throws javax.mail.MessagingException
meth public void setText(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void writeTo(java.io.OutputStream,[Ljava.lang.String;) throws java.io.IOException, javax.mail.MessagingException
meth public java.lang.String getHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void addHeaderLine(java.lang.String) throws javax.mail.MessagingException
meth public java.util.Enumeration getAllHeaderLines() throws javax.mail.MessagingException
meth public java.util.Enumeration getMatchingHeaderLines([Ljava.lang.String;) throws javax.mail.MessagingException
meth public java.util.Enumeration getNonMatchingHeaderLines([Ljava.lang.String;) throws javax.mail.MessagingException
fld  public static final java.lang.String javax.mail.Part.ATTACHMENT
fld  public static final java.lang.String javax.mail.Part.INLINE
CLSS javax.mail.event.MessageChangedListener
supr null
intf interface java.util.EventListener
meth public abstract void messageChanged(javax.mail.event.MessageChangedEvent)
CLSS javax.mail.event.FolderAdapter
supr class java.lang.Object
intf interface javax.mail.event.FolderListener
cons public javax.mail.event.FolderAdapter()
meth public final java.lang.Class getClass()
meth public int hashCode()
meth public boolean equals(java.lang.Object)
meth public java.lang.String toString()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public final void wait() throws java.lang.InterruptedException
meth public void folderCreated(javax.mail.event.FolderEvent)
meth public void folderRenamed(javax.mail.event.FolderEvent)
meth public void folderDeleted(javax.mail.event.FolderEvent)
