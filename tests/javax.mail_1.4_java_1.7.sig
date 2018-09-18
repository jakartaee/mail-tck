#Signature file v4.0
#Version 

CLSS public java.io.BufferedInputStream
cons public BufferedInputStream(java.io.InputStream)
cons public BufferedInputStream(java.io.InputStream,int)
fld protected int count
fld protected int marklimit
fld protected int markpos
fld protected int pos
fld protected volatile byte[] buf
meth public boolean markSupported()
meth public int available() throws java.io.IOException
meth public int read() throws java.io.IOException
meth public int read(byte[],int,int) throws java.io.IOException
meth public long skip(long) throws java.io.IOException
meth public void close() throws java.io.IOException
meth public void mark(int)
meth public void reset() throws java.io.IOException
supr java.io.FilterInputStream
hfds bufUpdater,defaultBufferSize

CLSS public java.io.ByteArrayInputStream
cons public ByteArrayInputStream(byte[])
cons public ByteArrayInputStream(byte[],int,int)
fld protected byte[] buf
fld protected int count
fld protected int mark
fld protected int pos
meth public boolean markSupported()
meth public int available()
meth public int read()
meth public int read(byte[],int,int)
meth public long skip(long)
meth public void close() throws java.io.IOException
meth public void mark(int)
meth public void reset()
supr java.io.InputStream

CLSS public abstract interface java.io.Closeable
intf java.lang.AutoCloseable
meth public abstract void close() throws java.io.IOException

CLSS public java.io.FilterInputStream
cons protected FilterInputStream(java.io.InputStream)
fld protected volatile java.io.InputStream in
meth public boolean markSupported()
meth public int available() throws java.io.IOException
meth public int read() throws java.io.IOException
meth public int read(byte[]) throws java.io.IOException
meth public int read(byte[],int,int) throws java.io.IOException
meth public long skip(long) throws java.io.IOException
meth public void close() throws java.io.IOException
meth public void mark(int)
meth public void reset() throws java.io.IOException
supr java.io.InputStream

CLSS public abstract java.io.InputStream
cons public InputStream()
intf java.io.Closeable
meth public abstract int read() throws java.io.IOException
meth public boolean markSupported()
meth public int available() throws java.io.IOException
meth public int read(byte[]) throws java.io.IOException
meth public int read(byte[],int,int) throws java.io.IOException
meth public long skip(long) throws java.io.IOException
meth public void close() throws java.io.IOException
meth public void mark(int)
meth public void reset() throws java.io.IOException
supr java.lang.Object
hfds MAX_SKIP_BUFFER_SIZE

CLSS public abstract interface java.io.Serializable

CLSS public abstract interface java.lang.AutoCloseable
meth public abstract void close() throws java.lang.Exception

CLSS public abstract interface java.lang.Cloneable

CLSS public java.lang.Exception
cons protected Exception(java.lang.String,java.lang.Throwable,boolean,boolean)
cons public Exception()
cons public Exception(java.lang.String)
cons public Exception(java.lang.String,java.lang.Throwable)
cons public Exception(java.lang.Throwable)
supr java.lang.Throwable
hfds serialVersionUID

CLSS public java.lang.Object
cons public Object()
meth protected java.lang.Object clone() throws java.lang.CloneNotSupportedException
meth protected void finalize() throws java.lang.Throwable
meth public boolean equals(java.lang.Object)
meth public final java.lang.Class<?> getClass()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait() throws java.lang.InterruptedException
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public int hashCode()
meth public java.lang.String toString()

CLSS public java.lang.Throwable
cons protected Throwable(java.lang.String,java.lang.Throwable,boolean,boolean)
cons public Throwable()
cons public Throwable(java.lang.String)
cons public Throwable(java.lang.String,java.lang.Throwable)
cons public Throwable(java.lang.Throwable)
intf java.io.Serializable
meth public final java.lang.Throwable[] getSuppressed()
meth public final void addSuppressed(java.lang.Throwable)
meth public java.lang.StackTraceElement[] getStackTrace()
meth public java.lang.String getLocalizedMessage()
meth public java.lang.String getMessage()
meth public java.lang.String toString()
meth public java.lang.Throwable fillInStackTrace()
meth public java.lang.Throwable getCause()
meth public java.lang.Throwable initCause(java.lang.Throwable)
meth public void printStackTrace()
meth public void printStackTrace(java.io.PrintStream)
meth public void printStackTrace(java.io.PrintWriter)
meth public void setStackTrace(java.lang.StackTraceElement[])
supr java.lang.Object
hfds CAUSE_CAPTION,EMPTY_THROWABLE_ARRAY,NULL_CAUSE_MESSAGE,SELF_SUPPRESSION_MESSAGE,SUPPRESSED_CAPTION,SUPPRESSED_SENTINEL,UNASSIGNED_STACK,backtrace,cause,detailMessage,serialVersionUID,stackTrace,suppressedExceptions
hcls PrintStreamOrWriter,SentinelHolder,WrappedPrintStream,WrappedPrintWriter

CLSS public abstract java.text.DateFormat
cons protected DateFormat()
fld protected java.text.NumberFormat numberFormat
fld protected java.util.Calendar calendar
fld public final static int AM_PM_FIELD = 14
fld public final static int DATE_FIELD = 3
fld public final static int DAY_OF_WEEK_FIELD = 9
fld public final static int DAY_OF_WEEK_IN_MONTH_FIELD = 11
fld public final static int DAY_OF_YEAR_FIELD = 10
fld public final static int DEFAULT = 2
fld public final static int ERA_FIELD = 0
fld public final static int FULL = 0
fld public final static int HOUR0_FIELD = 16
fld public final static int HOUR1_FIELD = 15
fld public final static int HOUR_OF_DAY0_FIELD = 5
fld public final static int HOUR_OF_DAY1_FIELD = 4
fld public final static int LONG = 1
fld public final static int MEDIUM = 2
fld public final static int MILLISECOND_FIELD = 8
fld public final static int MINUTE_FIELD = 6
fld public final static int MONTH_FIELD = 2
fld public final static int SECOND_FIELD = 7
fld public final static int SHORT = 3
fld public final static int TIMEZONE_FIELD = 17
fld public final static int WEEK_OF_MONTH_FIELD = 13
fld public final static int WEEK_OF_YEAR_FIELD = 12
fld public final static int YEAR_FIELD = 1
innr public static Field
meth public abstract java.lang.StringBuffer format(java.util.Date,java.lang.StringBuffer,java.text.FieldPosition)
meth public abstract java.util.Date parse(java.lang.String,java.text.ParsePosition)
meth public boolean equals(java.lang.Object)
meth public boolean isLenient()
meth public final java.lang.String format(java.util.Date)
meth public final java.lang.StringBuffer format(java.lang.Object,java.lang.StringBuffer,java.text.FieldPosition)
meth public final static java.text.DateFormat getDateInstance()
meth public final static java.text.DateFormat getDateInstance(int)
meth public final static java.text.DateFormat getDateInstance(int,java.util.Locale)
meth public final static java.text.DateFormat getDateTimeInstance()
meth public final static java.text.DateFormat getDateTimeInstance(int,int)
meth public final static java.text.DateFormat getDateTimeInstance(int,int,java.util.Locale)
meth public final static java.text.DateFormat getInstance()
meth public final static java.text.DateFormat getTimeInstance()
meth public final static java.text.DateFormat getTimeInstance(int)
meth public final static java.text.DateFormat getTimeInstance(int,java.util.Locale)
meth public int hashCode()
meth public java.lang.Object clone()
meth public java.lang.Object parseObject(java.lang.String,java.text.ParsePosition)
meth public java.text.NumberFormat getNumberFormat()
meth public java.util.Calendar getCalendar()
meth public java.util.Date parse(java.lang.String) throws java.text.ParseException
meth public java.util.TimeZone getTimeZone()
meth public static java.util.Locale[] getAvailableLocales()
meth public void setCalendar(java.util.Calendar)
meth public void setLenient(boolean)
meth public void setNumberFormat(java.text.NumberFormat)
meth public void setTimeZone(java.util.TimeZone)
supr java.text.Format
hfds serialVersionUID
hcls DateFormatGetter

CLSS public abstract java.text.Format
cons protected Format()
innr public static Field
intf java.io.Serializable
intf java.lang.Cloneable
meth public abstract java.lang.Object parseObject(java.lang.String,java.text.ParsePosition)
meth public abstract java.lang.StringBuffer format(java.lang.Object,java.lang.StringBuffer,java.text.FieldPosition)
meth public final java.lang.String format(java.lang.Object)
meth public java.lang.Object clone()
meth public java.lang.Object parseObject(java.lang.String) throws java.text.ParseException
meth public java.text.AttributedCharacterIterator formatToCharacterIterator(java.lang.Object)
supr java.lang.Object
hfds serialVersionUID
hcls FieldDelegate

CLSS public java.text.SimpleDateFormat
cons public SimpleDateFormat()
cons public SimpleDateFormat(java.lang.String)
cons public SimpleDateFormat(java.lang.String,java.text.DateFormatSymbols)
cons public SimpleDateFormat(java.lang.String,java.util.Locale)
meth public boolean equals(java.lang.Object)
meth public int hashCode()
meth public java.lang.Object clone()
meth public java.lang.String toLocalizedPattern()
meth public java.lang.String toPattern()
meth public java.lang.StringBuffer format(java.util.Date,java.lang.StringBuffer,java.text.FieldPosition)
meth public java.text.AttributedCharacterIterator formatToCharacterIterator(java.lang.Object)
meth public java.text.DateFormatSymbols getDateFormatSymbols()
meth public java.util.Date get2DigitYearStart()
meth public java.util.Date parse(java.lang.String,java.text.ParsePosition)
meth public void applyLocalizedPattern(java.lang.String)
meth public void applyPattern(java.lang.String)
meth public void set2DigitYearStart(java.util.Date)
meth public void setDateFormatSymbols(java.text.DateFormatSymbols)
supr java.text.DateFormat
hfds GMT,MILLIS_PER_MINUTE,PATTERN_INDEX_TO_CALENDAR_FIELD,PATTERN_INDEX_TO_DATE_FORMAT_FIELD,PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID,TAG_QUOTE_ASCII_CHAR,TAG_QUOTE_CHARS,cachedLocaleData,cachedNumberFormatData,compiledPattern,currentSerialVersion,defaultCenturyStart,defaultCenturyStartYear,formatData,hasFollowingMinusSign,locale,minusSign,originalNumberFormat,originalNumberPattern,pattern,serialVersionOnStream,serialVersionUID,useDateFormatSymbols,zeroDigit

CLSS public abstract interface java.util.EventListener

CLSS public java.util.EventObject
cons public EventObject(java.lang.Object)
fld protected java.lang.Object source
intf java.io.Serializable
meth public java.lang.Object getSource()
meth public java.lang.String toString()
supr java.lang.Object
hfds serialVersionUID

CLSS public abstract interface javax.activation.DataSource
meth public abstract java.io.InputStream getInputStream() throws java.io.IOException
meth public abstract java.io.OutputStream getOutputStream() throws java.io.IOException
meth public abstract java.lang.String getContentType()
meth public abstract java.lang.String getName()

CLSS public abstract javax.mail.Address
cons public Address()
intf java.io.Serializable
meth public abstract boolean equals(java.lang.Object)
meth public abstract java.lang.String getType()
meth public abstract java.lang.String toString()
supr java.lang.Object
hfds serialVersionUID

CLSS public javax.mail.AuthenticationFailedException
cons public AuthenticationFailedException()
cons public AuthenticationFailedException(java.lang.String)
supr javax.mail.MessagingException
hfds serialVersionUID

CLSS public abstract javax.mail.Authenticator
cons public Authenticator()
meth protected final int getRequestingPort()
meth protected final java.lang.String getDefaultUserName()
meth protected final java.lang.String getRequestingPrompt()
meth protected final java.lang.String getRequestingProtocol()
meth protected final java.net.InetAddress getRequestingSite()
meth protected javax.mail.PasswordAuthentication getPasswordAuthentication()
supr java.lang.Object
hfds requestingPort,requestingPrompt,requestingProtocol,requestingSite,requestingUserName

CLSS public abstract javax.mail.BodyPart
cons public BodyPart()
fld protected javax.mail.Multipart parent
intf javax.mail.Part
meth public javax.mail.Multipart getParent()
supr java.lang.Object

CLSS public javax.mail.FetchProfile
cons public FetchProfile()
innr public static Item
meth public boolean contains(java.lang.String)
meth public boolean contains(javax.mail.FetchProfile$Item)
meth public java.lang.String[] getHeaderNames()
meth public javax.mail.FetchProfile$Item[] getItems()
meth public void add(java.lang.String)
meth public void add(javax.mail.FetchProfile$Item)
supr java.lang.Object
hfds headers,specials

CLSS public static javax.mail.FetchProfile$Item
cons protected Item(java.lang.String)
fld public final static javax.mail.FetchProfile$Item CONTENT_INFO
fld public final static javax.mail.FetchProfile$Item ENVELOPE
fld public final static javax.mail.FetchProfile$Item FLAGS
meth public java.lang.String toString()
supr java.lang.Object
hfds name

CLSS public javax.mail.Flags
cons public Flags()
cons public Flags(java.lang.String)
cons public Flags(javax.mail.Flags$Flag)
cons public Flags(javax.mail.Flags)
innr public final static Flag
intf java.io.Serializable
intf java.lang.Cloneable
meth public boolean contains(java.lang.String)
meth public boolean contains(javax.mail.Flags$Flag)
meth public boolean contains(javax.mail.Flags)
meth public boolean equals(java.lang.Object)
meth public int hashCode()
meth public java.lang.Object clone()
meth public java.lang.String[] getUserFlags()
meth public javax.mail.Flags$Flag[] getSystemFlags()
meth public void add(java.lang.String)
meth public void add(javax.mail.Flags$Flag)
meth public void add(javax.mail.Flags)
meth public void remove(java.lang.String)
meth public void remove(javax.mail.Flags$Flag)
meth public void remove(javax.mail.Flags)
supr java.lang.Object
hfds ANSWERED_BIT,DELETED_BIT,DRAFT_BIT,FLAGGED_BIT,RECENT_BIT,SEEN_BIT,USER_BIT,serialVersionUID,system_flags,user_flags

CLSS public final static javax.mail.Flags$Flag
fld public final static javax.mail.Flags$Flag ANSWERED
fld public final static javax.mail.Flags$Flag DELETED
fld public final static javax.mail.Flags$Flag DRAFT
fld public final static javax.mail.Flags$Flag FLAGGED
fld public final static javax.mail.Flags$Flag RECENT
fld public final static javax.mail.Flags$Flag SEEN
fld public final static javax.mail.Flags$Flag USER
supr java.lang.Object
hfds bit

CLSS public abstract javax.mail.Folder
cons protected Folder(javax.mail.Store)
fld protected int mode
fld protected javax.mail.Store store
fld public final static int HOLDS_FOLDERS = 2
fld public final static int HOLDS_MESSAGES = 1
fld public final static int READ_ONLY = 1
fld public final static int READ_WRITE = 2
meth protected void finalize() throws java.lang.Throwable
meth protected void notifyConnectionListeners(int)
meth protected void notifyFolderListeners(int)
meth protected void notifyFolderRenamedListeners(javax.mail.Folder)
meth protected void notifyMessageAddedListeners(javax.mail.Message[])
meth protected void notifyMessageChangedListeners(int,javax.mail.Message)
meth protected void notifyMessageRemovedListeners(boolean,javax.mail.Message[])
meth public abstract boolean create(int) throws javax.mail.MessagingException
meth public abstract boolean delete(boolean) throws javax.mail.MessagingException
meth public abstract boolean exists() throws javax.mail.MessagingException
meth public abstract boolean hasNewMessages() throws javax.mail.MessagingException
meth public abstract boolean isOpen()
meth public abstract boolean renameTo(javax.mail.Folder) throws javax.mail.MessagingException
meth public abstract char getSeparator() throws javax.mail.MessagingException
meth public abstract int getMessageCount() throws javax.mail.MessagingException
meth public abstract int getType() throws javax.mail.MessagingException
meth public abstract java.lang.String getFullName()
meth public abstract java.lang.String getName()
meth public abstract javax.mail.Flags getPermanentFlags()
meth public abstract javax.mail.Folder getFolder(java.lang.String) throws javax.mail.MessagingException
meth public abstract javax.mail.Folder getParent() throws javax.mail.MessagingException
meth public abstract javax.mail.Folder[] list(java.lang.String) throws javax.mail.MessagingException
meth public abstract javax.mail.Message getMessage(int) throws javax.mail.MessagingException
meth public abstract javax.mail.Message[] expunge() throws javax.mail.MessagingException
meth public abstract void appendMessages(javax.mail.Message[]) throws javax.mail.MessagingException
meth public abstract void close(boolean) throws javax.mail.MessagingException
meth public abstract void open(int) throws javax.mail.MessagingException
meth public boolean isSubscribed()
meth public int getDeletedMessageCount() throws javax.mail.MessagingException
meth public int getMode()
meth public int getNewMessageCount() throws javax.mail.MessagingException
meth public int getUnreadMessageCount() throws javax.mail.MessagingException
meth public java.lang.String toString()
meth public javax.mail.Folder[] list() throws javax.mail.MessagingException
meth public javax.mail.Folder[] listSubscribed() throws javax.mail.MessagingException
meth public javax.mail.Folder[] listSubscribed(java.lang.String) throws javax.mail.MessagingException
meth public javax.mail.Message[] getMessages() throws javax.mail.MessagingException
meth public javax.mail.Message[] getMessages(int,int) throws javax.mail.MessagingException
meth public javax.mail.Message[] getMessages(int[]) throws javax.mail.MessagingException
meth public javax.mail.Message[] search(javax.mail.search.SearchTerm) throws javax.mail.MessagingException
meth public javax.mail.Message[] search(javax.mail.search.SearchTerm,javax.mail.Message[]) throws javax.mail.MessagingException
meth public javax.mail.Store getStore()
meth public javax.mail.URLName getURLName() throws javax.mail.MessagingException
meth public void addConnectionListener(javax.mail.event.ConnectionListener)
meth public void addFolderListener(javax.mail.event.FolderListener)
meth public void addMessageChangedListener(javax.mail.event.MessageChangedListener)
meth public void addMessageCountListener(javax.mail.event.MessageCountListener)
meth public void copyMessages(javax.mail.Message[],javax.mail.Folder) throws javax.mail.MessagingException
meth public void fetch(javax.mail.Message[],javax.mail.FetchProfile) throws javax.mail.MessagingException
meth public void removeConnectionListener(javax.mail.event.ConnectionListener)
meth public void removeFolderListener(javax.mail.event.FolderListener)
meth public void removeMessageChangedListener(javax.mail.event.MessageChangedListener)
meth public void removeMessageCountListener(javax.mail.event.MessageCountListener)
meth public void setFlags(int,int,javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public void setFlags(int[],javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public void setFlags(javax.mail.Message[],javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public void setSubscribed(boolean) throws javax.mail.MessagingException
supr java.lang.Object
hfds connectionListeners,folderListeners,messageChangedListeners,messageCountListeners,q,qLock
hcls TerminatorEvent

CLSS public javax.mail.FolderClosedException
cons public FolderClosedException(javax.mail.Folder)
cons public FolderClosedException(javax.mail.Folder,java.lang.String)
meth public javax.mail.Folder getFolder()
supr javax.mail.MessagingException
hfds folder,serialVersionUID

CLSS public javax.mail.FolderNotFoundException
cons public FolderNotFoundException()
cons public FolderNotFoundException(java.lang.String,javax.mail.Folder)
cons public FolderNotFoundException(javax.mail.Folder)
cons public FolderNotFoundException(javax.mail.Folder,java.lang.String)
meth public javax.mail.Folder getFolder()
supr javax.mail.MessagingException
hfds folder,serialVersionUID

CLSS public javax.mail.Header
cons public Header(java.lang.String,java.lang.String)
fld protected java.lang.String name
fld protected java.lang.String value
meth public java.lang.String getName()
meth public java.lang.String getValue()
supr java.lang.Object

CLSS public javax.mail.IllegalWriteException
cons public IllegalWriteException()
cons public IllegalWriteException(java.lang.String)
supr javax.mail.MessagingException
hfds serialVersionUID

CLSS public abstract javax.mail.Message
cons protected Message()
cons protected Message(javax.mail.Folder,int)
cons protected Message(javax.mail.Session)
fld protected boolean expunged
fld protected int msgnum
fld protected javax.mail.Folder folder
fld protected javax.mail.Session session
innr public static RecipientType
intf javax.mail.Part
meth protected void setExpunged(boolean)
meth protected void setMessageNumber(int)
meth public abstract java.lang.String getSubject() throws javax.mail.MessagingException
meth public abstract java.util.Date getReceivedDate() throws javax.mail.MessagingException
meth public abstract java.util.Date getSentDate() throws javax.mail.MessagingException
meth public abstract javax.mail.Address[] getFrom() throws javax.mail.MessagingException
meth public abstract javax.mail.Address[] getRecipients(javax.mail.Message$RecipientType) throws javax.mail.MessagingException
meth public abstract javax.mail.Flags getFlags() throws javax.mail.MessagingException
meth public abstract javax.mail.Message reply(boolean) throws javax.mail.MessagingException
meth public abstract void addFrom(javax.mail.Address[]) throws javax.mail.MessagingException
meth public abstract void addRecipients(javax.mail.Message$RecipientType,javax.mail.Address[]) throws javax.mail.MessagingException
meth public abstract void saveChanges() throws javax.mail.MessagingException
meth public abstract void setFlags(javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public abstract void setFrom() throws javax.mail.MessagingException
meth public abstract void setFrom(javax.mail.Address) throws javax.mail.MessagingException
meth public abstract void setRecipients(javax.mail.Message$RecipientType,javax.mail.Address[]) throws javax.mail.MessagingException
meth public abstract void setSentDate(java.util.Date) throws javax.mail.MessagingException
meth public abstract void setSubject(java.lang.String) throws javax.mail.MessagingException
meth public boolean isExpunged()
meth public boolean isSet(javax.mail.Flags$Flag) throws javax.mail.MessagingException
meth public boolean match(javax.mail.search.SearchTerm) throws javax.mail.MessagingException
meth public int getMessageNumber()
meth public javax.mail.Address[] getAllRecipients() throws javax.mail.MessagingException
meth public javax.mail.Address[] getReplyTo() throws javax.mail.MessagingException
meth public javax.mail.Folder getFolder()
meth public void addRecipient(javax.mail.Message$RecipientType,javax.mail.Address) throws javax.mail.MessagingException
meth public void setFlag(javax.mail.Flags$Flag,boolean) throws javax.mail.MessagingException
meth public void setRecipient(javax.mail.Message$RecipientType,javax.mail.Address) throws javax.mail.MessagingException
meth public void setReplyTo(javax.mail.Address[]) throws javax.mail.MessagingException
supr java.lang.Object

CLSS public static javax.mail.Message$RecipientType
cons protected RecipientType(java.lang.String)
fld protected java.lang.String type
fld public final static javax.mail.Message$RecipientType BCC
fld public final static javax.mail.Message$RecipientType CC
fld public final static javax.mail.Message$RecipientType TO
intf java.io.Serializable
meth protected java.lang.Object readResolve() throws java.io.ObjectStreamException
meth public java.lang.String toString()
supr java.lang.Object
hfds serialVersionUID

CLSS public abstract interface javax.mail.MessageAware
meth public abstract javax.mail.MessageContext getMessageContext()

CLSS public javax.mail.MessageContext
cons public MessageContext(javax.mail.Part)
meth public javax.mail.Message getMessage()
meth public javax.mail.Part getPart()
meth public javax.mail.Session getSession()
supr java.lang.Object
hfds part

CLSS public javax.mail.MessageRemovedException
cons public MessageRemovedException()
cons public MessageRemovedException(java.lang.String)
supr javax.mail.MessagingException
hfds serialVersionUID

CLSS public javax.mail.MessagingException
cons public MessagingException()
cons public MessagingException(java.lang.String)
cons public MessagingException(java.lang.String,java.lang.Exception)
meth public boolean setNextException(java.lang.Exception)
meth public java.lang.Exception getNextException()
meth public java.lang.String toString()
meth public java.lang.Throwable getCause()
supr java.lang.Exception
hfds next,serialVersionUID

CLSS public javax.mail.MethodNotSupportedException
cons public MethodNotSupportedException()
cons public MethodNotSupportedException(java.lang.String)
supr javax.mail.MessagingException
hfds serialVersionUID

CLSS public abstract javax.mail.Multipart
cons protected Multipart()
fld protected java.lang.String contentType
fld protected java.util.Vector parts
fld protected javax.mail.Part parent
meth protected void setMultipartDataSource(javax.mail.MultipartDataSource) throws javax.mail.MessagingException
meth public abstract void writeTo(java.io.OutputStream) throws java.io.IOException,javax.mail.MessagingException
meth public boolean removeBodyPart(javax.mail.BodyPart) throws javax.mail.MessagingException
meth public int getCount() throws javax.mail.MessagingException
meth public java.lang.String getContentType()
meth public javax.mail.BodyPart getBodyPart(int) throws javax.mail.MessagingException
meth public javax.mail.Part getParent()
meth public void addBodyPart(javax.mail.BodyPart) throws javax.mail.MessagingException
meth public void addBodyPart(javax.mail.BodyPart,int) throws javax.mail.MessagingException
meth public void removeBodyPart(int) throws javax.mail.MessagingException
meth public void setParent(javax.mail.Part)
supr java.lang.Object

CLSS public abstract interface javax.mail.MultipartDataSource
intf javax.activation.DataSource
meth public abstract int getCount()
meth public abstract javax.mail.BodyPart getBodyPart(int) throws javax.mail.MessagingException

CLSS public javax.mail.NoSuchProviderException
cons public NoSuchProviderException()
cons public NoSuchProviderException(java.lang.String)
supr javax.mail.MessagingException
hfds serialVersionUID

CLSS public abstract interface javax.mail.Part
fld public final static java.lang.String ATTACHMENT = "attachment"
fld public final static java.lang.String INLINE = "inline"
meth public abstract boolean isMimeType(java.lang.String) throws javax.mail.MessagingException
meth public abstract int getLineCount() throws javax.mail.MessagingException
meth public abstract int getSize() throws javax.mail.MessagingException
meth public abstract java.io.InputStream getInputStream() throws java.io.IOException,javax.mail.MessagingException
meth public abstract java.lang.Object getContent() throws java.io.IOException,javax.mail.MessagingException
meth public abstract java.lang.String getContentType() throws javax.mail.MessagingException
meth public abstract java.lang.String getDescription() throws javax.mail.MessagingException
meth public abstract java.lang.String getDisposition() throws javax.mail.MessagingException
meth public abstract java.lang.String getFileName() throws javax.mail.MessagingException
meth public abstract java.lang.String[] getHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getAllHeaders() throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getMatchingHeaders(java.lang.String[]) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getNonMatchingHeaders(java.lang.String[]) throws javax.mail.MessagingException
meth public abstract javax.activation.DataHandler getDataHandler() throws javax.mail.MessagingException
meth public abstract void addHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void removeHeader(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setContent(java.lang.Object,java.lang.String) throws javax.mail.MessagingException
meth public abstract void setContent(javax.mail.Multipart) throws javax.mail.MessagingException
meth public abstract void setDataHandler(javax.activation.DataHandler) throws javax.mail.MessagingException
meth public abstract void setDescription(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setDisposition(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setFileName(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String) throws javax.mail.MessagingException
meth public abstract void writeTo(java.io.OutputStream) throws java.io.IOException,javax.mail.MessagingException

CLSS public final javax.mail.PasswordAuthentication
cons public PasswordAuthentication(java.lang.String,java.lang.String)
meth public java.lang.String getPassword()
meth public java.lang.String getUserName()
supr java.lang.Object
hfds password,userName

CLSS public javax.mail.Provider
cons public Provider(javax.mail.Provider$Type,java.lang.String,java.lang.String,java.lang.String,java.lang.String)
innr public static Type
meth public java.lang.String getClassName()
meth public java.lang.String getProtocol()
meth public java.lang.String getVendor()
meth public java.lang.String getVersion()
meth public java.lang.String toString()
meth public javax.mail.Provider$Type getType()
supr java.lang.Object
hfds className,protocol,type,vendor,version

CLSS public static javax.mail.Provider$Type
fld public final static javax.mail.Provider$Type STORE
fld public final static javax.mail.Provider$Type TRANSPORT
meth public java.lang.String toString()
supr java.lang.Object
hfds type

CLSS public javax.mail.Quota
cons public Quota(java.lang.String)
fld public java.lang.String quotaRoot
fld public javax.mail.Quota$Resource[] resources
innr public static Resource
meth public void setResourceLimit(java.lang.String,long)
supr java.lang.Object

CLSS public static javax.mail.Quota$Resource
cons public Resource(java.lang.String,long,long)
fld public java.lang.String name
fld public long limit
fld public long usage
supr java.lang.Object

CLSS public abstract interface javax.mail.QuotaAwareStore
meth public abstract javax.mail.Quota[] getQuota(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setQuota(javax.mail.Quota) throws javax.mail.MessagingException

CLSS public javax.mail.ReadOnlyFolderException
cons public ReadOnlyFolderException(javax.mail.Folder)
cons public ReadOnlyFolderException(javax.mail.Folder,java.lang.String)
meth public javax.mail.Folder getFolder()
supr javax.mail.MessagingException
hfds folder,serialVersionUID

CLSS public javax.mail.SendFailedException
cons public SendFailedException()
cons public SendFailedException(java.lang.String)
cons public SendFailedException(java.lang.String,java.lang.Exception)
cons public SendFailedException(java.lang.String,java.lang.Exception,javax.mail.Address[],javax.mail.Address[],javax.mail.Address[])
fld protected javax.mail.Address[] invalid
fld protected javax.mail.Address[] validSent
fld protected javax.mail.Address[] validUnsent
meth public javax.mail.Address[] getInvalidAddresses()
meth public javax.mail.Address[] getValidSentAddresses()
meth public javax.mail.Address[] getValidUnsentAddresses()
supr javax.mail.MessagingException
hfds serialVersionUID

CLSS public abstract javax.mail.Service
cons protected Service(javax.mail.Session,javax.mail.URLName)
fld protected boolean debug
fld protected javax.mail.Session session
fld protected javax.mail.URLName url
meth protected boolean protocolConnect(java.lang.String,int,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth protected void finalize() throws java.lang.Throwable
meth protected void notifyConnectionListeners(int)
meth protected void queueEvent(javax.mail.event.MailEvent,java.util.Vector)
meth protected void setConnected(boolean)
meth protected void setURLName(javax.mail.URLName)
meth public boolean isConnected()
meth public java.lang.String toString()
meth public javax.mail.URLName getURLName()
meth public void addConnectionListener(javax.mail.event.ConnectionListener)
meth public void close() throws javax.mail.MessagingException
meth public void connect() throws javax.mail.MessagingException
meth public void connect(java.lang.String,int,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void connect(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void connect(java.lang.String,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void removeConnectionListener(javax.mail.event.ConnectionListener)
supr java.lang.Object
hfds connected,connectionListeners,q,qLock
hcls TerminatorEvent

CLSS public final javax.mail.Session
meth public boolean getDebug()
meth public java.io.PrintStream getDebugOut()
meth public java.lang.String getProperty(java.lang.String)
meth public java.util.Properties getProperties()
meth public javax.mail.Folder getFolder(javax.mail.URLName) throws javax.mail.MessagingException
meth public javax.mail.PasswordAuthentication getPasswordAuthentication(javax.mail.URLName)
meth public javax.mail.PasswordAuthentication requestPasswordAuthentication(java.net.InetAddress,int,java.lang.String,java.lang.String,java.lang.String)
meth public javax.mail.Provider getProvider(java.lang.String) throws javax.mail.NoSuchProviderException
meth public javax.mail.Provider[] getProviders()
meth public javax.mail.Store getStore() throws javax.mail.NoSuchProviderException
meth public javax.mail.Store getStore(java.lang.String) throws javax.mail.NoSuchProviderException
meth public javax.mail.Store getStore(javax.mail.Provider) throws javax.mail.NoSuchProviderException
meth public javax.mail.Store getStore(javax.mail.URLName) throws javax.mail.NoSuchProviderException
meth public javax.mail.Transport getTransport() throws javax.mail.NoSuchProviderException
meth public javax.mail.Transport getTransport(java.lang.String) throws javax.mail.NoSuchProviderException
meth public javax.mail.Transport getTransport(javax.mail.Address) throws javax.mail.NoSuchProviderException
meth public javax.mail.Transport getTransport(javax.mail.Provider) throws javax.mail.NoSuchProviderException
meth public javax.mail.Transport getTransport(javax.mail.URLName) throws javax.mail.NoSuchProviderException
meth public static javax.mail.Session getDefaultInstance(java.util.Properties)
meth public static javax.mail.Session getDefaultInstance(java.util.Properties,javax.mail.Authenticator)
meth public static javax.mail.Session getInstance(java.util.Properties)
meth public static javax.mail.Session getInstance(java.util.Properties,javax.mail.Authenticator)
meth public void addProvider(javax.mail.Provider)
meth public void setDebug(boolean)
meth public void setDebugOut(java.io.PrintStream)
meth public void setPasswordAuthentication(javax.mail.URLName,javax.mail.PasswordAuthentication)
meth public void setProtocolForAddress(java.lang.String,java.lang.String)
meth public void setProvider(javax.mail.Provider) throws javax.mail.NoSuchProviderException
supr java.lang.Object
hfds addressMap,authTable,authenticator,class$javax$mail$Session,class$javax$mail$URLName,debug,defaultSession,out,props,providers,providersByClassName,providersByProtocol

CLSS public abstract javax.mail.Store
cons protected Store(javax.mail.Session,javax.mail.URLName)
meth protected void notifyFolderListeners(int,javax.mail.Folder)
meth protected void notifyFolderRenamedListeners(javax.mail.Folder,javax.mail.Folder)
meth protected void notifyStoreListeners(int,java.lang.String)
meth public abstract javax.mail.Folder getDefaultFolder() throws javax.mail.MessagingException
meth public abstract javax.mail.Folder getFolder(java.lang.String) throws javax.mail.MessagingException
meth public abstract javax.mail.Folder getFolder(javax.mail.URLName) throws javax.mail.MessagingException
meth public javax.mail.Folder[] getPersonalNamespaces() throws javax.mail.MessagingException
meth public javax.mail.Folder[] getSharedNamespaces() throws javax.mail.MessagingException
meth public javax.mail.Folder[] getUserNamespaces(java.lang.String) throws javax.mail.MessagingException
meth public void addFolderListener(javax.mail.event.FolderListener)
meth public void addStoreListener(javax.mail.event.StoreListener)
meth public void removeFolderListener(javax.mail.event.FolderListener)
meth public void removeStoreListener(javax.mail.event.StoreListener)
supr javax.mail.Service
hfds folderListeners,storeListeners

CLSS public javax.mail.StoreClosedException
cons public StoreClosedException(javax.mail.Store)
cons public StoreClosedException(javax.mail.Store,java.lang.String)
meth public javax.mail.Store getStore()
supr javax.mail.MessagingException
hfds serialVersionUID,store

CLSS public abstract javax.mail.Transport
cons public Transport(javax.mail.Session,javax.mail.URLName)
meth protected void notifyTransportListeners(int,javax.mail.Address[],javax.mail.Address[],javax.mail.Address[],javax.mail.Message)
meth public abstract void sendMessage(javax.mail.Message,javax.mail.Address[]) throws javax.mail.MessagingException
meth public static void send(javax.mail.Message) throws javax.mail.MessagingException
meth public static void send(javax.mail.Message,javax.mail.Address[]) throws javax.mail.MessagingException
meth public void addTransportListener(javax.mail.event.TransportListener)
meth public void removeTransportListener(javax.mail.event.TransportListener)
supr javax.mail.Service
hfds transportListeners

CLSS public abstract interface javax.mail.UIDFolder
fld public final static long LASTUID = -1
innr public static FetchProfileItem
meth public abstract javax.mail.Message getMessageByUID(long) throws javax.mail.MessagingException
meth public abstract javax.mail.Message[] getMessagesByUID(long,long) throws javax.mail.MessagingException
meth public abstract javax.mail.Message[] getMessagesByUID(long[]) throws javax.mail.MessagingException
meth public abstract long getUID(javax.mail.Message) throws javax.mail.MessagingException
meth public abstract long getUIDValidity() throws javax.mail.MessagingException

CLSS public static javax.mail.UIDFolder$FetchProfileItem
cons protected FetchProfileItem(java.lang.String)
fld public final static javax.mail.UIDFolder$FetchProfileItem UID
supr javax.mail.FetchProfile$Item

CLSS public javax.mail.URLName
cons public URLName(java.lang.String)
cons public URLName(java.lang.String,java.lang.String,int,java.lang.String,java.lang.String,java.lang.String)
cons public URLName(java.net.URL)
fld protected java.lang.String fullURL
meth protected void parseString(java.lang.String)
meth public boolean equals(java.lang.Object)
meth public int getPort()
meth public int hashCode()
meth public java.lang.String getFile()
meth public java.lang.String getHost()
meth public java.lang.String getPassword()
meth public java.lang.String getProtocol()
meth public java.lang.String getRef()
meth public java.lang.String getUsername()
meth public java.lang.String toString()
meth public java.net.URL getURL() throws java.net.MalformedURLException
supr java.lang.Object
hfds caseDiff,doEncode,dontNeedEncoding,file,hashCode,host,hostAddress,hostAddressKnown,password,port,protocol,ref,username

CLSS public abstract javax.mail.event.ConnectionAdapter
cons public ConnectionAdapter()
intf javax.mail.event.ConnectionListener
meth public void closed(javax.mail.event.ConnectionEvent)
meth public void disconnected(javax.mail.event.ConnectionEvent)
meth public void opened(javax.mail.event.ConnectionEvent)
supr java.lang.Object

CLSS public javax.mail.event.ConnectionEvent
cons public ConnectionEvent(java.lang.Object,int)
fld protected int type
fld public final static int CLOSED = 3
fld public final static int DISCONNECTED = 2
fld public final static int OPENED = 1
meth public int getType()
meth public void dispatch(java.lang.Object)
supr javax.mail.event.MailEvent
hfds serialVersionUID

CLSS public abstract interface javax.mail.event.ConnectionListener
intf java.util.EventListener
meth public abstract void closed(javax.mail.event.ConnectionEvent)
meth public abstract void disconnected(javax.mail.event.ConnectionEvent)
meth public abstract void opened(javax.mail.event.ConnectionEvent)

CLSS public abstract javax.mail.event.FolderAdapter
cons public FolderAdapter()
intf javax.mail.event.FolderListener
meth public void folderCreated(javax.mail.event.FolderEvent)
meth public void folderDeleted(javax.mail.event.FolderEvent)
meth public void folderRenamed(javax.mail.event.FolderEvent)
supr java.lang.Object

CLSS public javax.mail.event.FolderEvent
cons public FolderEvent(java.lang.Object,javax.mail.Folder,int)
cons public FolderEvent(java.lang.Object,javax.mail.Folder,javax.mail.Folder,int)
fld protected int type
fld protected javax.mail.Folder folder
fld protected javax.mail.Folder newFolder
fld public final static int CREATED = 1
fld public final static int DELETED = 2
fld public final static int RENAMED = 3
meth public int getType()
meth public javax.mail.Folder getFolder()
meth public javax.mail.Folder getNewFolder()
meth public void dispatch(java.lang.Object)
supr javax.mail.event.MailEvent
hfds serialVersionUID

CLSS public abstract interface javax.mail.event.FolderListener
intf java.util.EventListener
meth public abstract void folderCreated(javax.mail.event.FolderEvent)
meth public abstract void folderDeleted(javax.mail.event.FolderEvent)
meth public abstract void folderRenamed(javax.mail.event.FolderEvent)

CLSS public abstract javax.mail.event.MailEvent
cons public MailEvent(java.lang.Object)
meth public abstract void dispatch(java.lang.Object)
supr java.util.EventObject
hfds serialVersionUID

CLSS public javax.mail.event.MessageChangedEvent
cons public MessageChangedEvent(java.lang.Object,int,javax.mail.Message)
fld protected int type
fld protected javax.mail.Message msg
fld public final static int ENVELOPE_CHANGED = 2
fld public final static int FLAGS_CHANGED = 1
meth public int getMessageChangeType()
meth public javax.mail.Message getMessage()
meth public void dispatch(java.lang.Object)
supr javax.mail.event.MailEvent
hfds serialVersionUID

CLSS public abstract interface javax.mail.event.MessageChangedListener
intf java.util.EventListener
meth public abstract void messageChanged(javax.mail.event.MessageChangedEvent)

CLSS public abstract javax.mail.event.MessageCountAdapter
cons public MessageCountAdapter()
intf javax.mail.event.MessageCountListener
meth public void messagesAdded(javax.mail.event.MessageCountEvent)
meth public void messagesRemoved(javax.mail.event.MessageCountEvent)
supr java.lang.Object

CLSS public javax.mail.event.MessageCountEvent
cons public MessageCountEvent(javax.mail.Folder,int,boolean,javax.mail.Message[])
fld protected boolean removed
fld protected int type
fld protected javax.mail.Message[] msgs
fld public final static int ADDED = 1
fld public final static int REMOVED = 2
meth public boolean isRemoved()
meth public int getType()
meth public javax.mail.Message[] getMessages()
meth public void dispatch(java.lang.Object)
supr javax.mail.event.MailEvent
hfds serialVersionUID

CLSS public abstract interface javax.mail.event.MessageCountListener
intf java.util.EventListener
meth public abstract void messagesAdded(javax.mail.event.MessageCountEvent)
meth public abstract void messagesRemoved(javax.mail.event.MessageCountEvent)

CLSS public javax.mail.event.StoreEvent
cons public StoreEvent(javax.mail.Store,int,java.lang.String)
fld protected int type
fld protected java.lang.String message
fld public final static int ALERT = 1
fld public final static int NOTICE = 2
meth public int getMessageType()
meth public java.lang.String getMessage()
meth public void dispatch(java.lang.Object)
supr javax.mail.event.MailEvent
hfds serialVersionUID

CLSS public abstract interface javax.mail.event.StoreListener
intf java.util.EventListener
meth public abstract void notification(javax.mail.event.StoreEvent)

CLSS public abstract javax.mail.event.TransportAdapter
cons public TransportAdapter()
intf javax.mail.event.TransportListener
meth public void messageDelivered(javax.mail.event.TransportEvent)
meth public void messageNotDelivered(javax.mail.event.TransportEvent)
meth public void messagePartiallyDelivered(javax.mail.event.TransportEvent)
supr java.lang.Object

CLSS public javax.mail.event.TransportEvent
cons public TransportEvent(javax.mail.Transport,int,javax.mail.Address[],javax.mail.Address[],javax.mail.Address[],javax.mail.Message)
fld protected int type
fld protected javax.mail.Address[] invalid
fld protected javax.mail.Address[] validSent
fld protected javax.mail.Address[] validUnsent
fld protected javax.mail.Message msg
fld public final static int MESSAGE_DELIVERED = 1
fld public final static int MESSAGE_NOT_DELIVERED = 2
fld public final static int MESSAGE_PARTIALLY_DELIVERED = 3
meth public int getType()
meth public javax.mail.Address[] getInvalidAddresses()
meth public javax.mail.Address[] getValidSentAddresses()
meth public javax.mail.Address[] getValidUnsentAddresses()
meth public javax.mail.Message getMessage()
meth public void dispatch(java.lang.Object)
supr javax.mail.event.MailEvent
hfds serialVersionUID

CLSS public abstract interface javax.mail.event.TransportListener
intf java.util.EventListener
meth public abstract void messageDelivered(javax.mail.event.TransportEvent)
meth public abstract void messageNotDelivered(javax.mail.event.TransportEvent)
meth public abstract void messagePartiallyDelivered(javax.mail.event.TransportEvent)

CLSS public javax.mail.internet.AddressException
cons public AddressException()
cons public AddressException(java.lang.String)
cons public AddressException(java.lang.String,java.lang.String)
cons public AddressException(java.lang.String,java.lang.String,int)
fld protected int pos
fld protected java.lang.String ref
meth public int getPos()
meth public java.lang.String getRef()
meth public java.lang.String toString()
supr javax.mail.internet.ParseException
hfds serialVersionUID

CLSS public javax.mail.internet.ContentDisposition
cons public ContentDisposition()
cons public ContentDisposition(java.lang.String) throws javax.mail.internet.ParseException
cons public ContentDisposition(java.lang.String,javax.mail.internet.ParameterList)
meth public java.lang.String getDisposition()
meth public java.lang.String getParameter(java.lang.String)
meth public java.lang.String toString()
meth public javax.mail.internet.ParameterList getParameterList()
meth public void setDisposition(java.lang.String)
meth public void setParameter(java.lang.String,java.lang.String)
meth public void setParameterList(javax.mail.internet.ParameterList)
supr java.lang.Object
hfds disposition,list

CLSS public javax.mail.internet.ContentType
cons public ContentType()
cons public ContentType(java.lang.String) throws javax.mail.internet.ParseException
cons public ContentType(java.lang.String,java.lang.String,javax.mail.internet.ParameterList)
meth public boolean match(java.lang.String)
meth public boolean match(javax.mail.internet.ContentType)
meth public java.lang.String getBaseType()
meth public java.lang.String getParameter(java.lang.String)
meth public java.lang.String getPrimaryType()
meth public java.lang.String getSubType()
meth public java.lang.String toString()
meth public javax.mail.internet.ParameterList getParameterList()
meth public void setParameter(java.lang.String,java.lang.String)
meth public void setParameterList(javax.mail.internet.ParameterList)
meth public void setPrimaryType(java.lang.String)
meth public void setSubType(java.lang.String)
supr java.lang.Object
hfds list,primaryType,subType

CLSS public javax.mail.internet.HeaderTokenizer
cons public HeaderTokenizer(java.lang.String)
cons public HeaderTokenizer(java.lang.String,java.lang.String)
cons public HeaderTokenizer(java.lang.String,java.lang.String,boolean)
fld public final static java.lang.String MIME = "()<>@,;:\u005c\u0022\u0009 []/?="
fld public final static java.lang.String RFC822 = "()<>@,;:\u005c\u0022\u0009 .[]"
innr public static Token
meth public java.lang.String getRemainder()
meth public javax.mail.internet.HeaderTokenizer$Token next() throws javax.mail.internet.ParseException
meth public javax.mail.internet.HeaderTokenizer$Token peek() throws javax.mail.internet.ParseException
supr java.lang.Object
hfds EOFToken,currentPos,delimiters,maxPos,nextPos,peekPos,skipComments,string

CLSS public static javax.mail.internet.HeaderTokenizer$Token
cons public Token(int,java.lang.String)
fld public final static int ATOM = -1
fld public final static int COMMENT = -3
fld public final static int EOF = -4
fld public final static int QUOTEDSTRING = -2
meth public int getType()
meth public java.lang.String getValue()
supr java.lang.Object
hfds type,value

CLSS public javax.mail.internet.InternetAddress
cons public InternetAddress()
cons public InternetAddress(java.lang.String) throws javax.mail.internet.AddressException
cons public InternetAddress(java.lang.String,boolean) throws javax.mail.internet.AddressException
cons public InternetAddress(java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
cons public InternetAddress(java.lang.String,java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
fld protected java.lang.String address
fld protected java.lang.String encodedPersonal
fld protected java.lang.String personal
intf java.lang.Cloneable
meth public boolean equals(java.lang.Object)
meth public boolean isGroup()
meth public int hashCode()
meth public java.lang.Object clone()
meth public java.lang.String getAddress()
meth public java.lang.String getPersonal()
meth public java.lang.String getType()
meth public java.lang.String toString()
meth public java.lang.String toUnicodeString()
meth public javax.mail.internet.InternetAddress[] getGroup(boolean) throws javax.mail.internet.AddressException
meth public static java.lang.String toString(javax.mail.Address[])
meth public static java.lang.String toString(javax.mail.Address[],int)
meth public static javax.mail.internet.InternetAddress getLocalAddress(javax.mail.Session)
meth public static javax.mail.internet.InternetAddress[] parse(java.lang.String) throws javax.mail.internet.AddressException
meth public static javax.mail.internet.InternetAddress[] parse(java.lang.String,boolean) throws javax.mail.internet.AddressException
meth public static javax.mail.internet.InternetAddress[] parseHeader(java.lang.String,boolean) throws javax.mail.internet.AddressException
meth public void setAddress(java.lang.String)
meth public void setPersonal(java.lang.String) throws java.io.UnsupportedEncodingException
meth public void setPersonal(java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
meth public void validate() throws javax.mail.internet.AddressException
supr javax.mail.Address
hfds ignoreBogusGroupName,rfc822phrase,serialVersionUID,specialsNoDot,specialsNoDotNoAt

CLSS public javax.mail.internet.InternetHeaders
cons public InternetHeaders()
cons public InternetHeaders(java.io.InputStream) throws javax.mail.MessagingException
fld protected java.util.List headers
innr protected final static InternetHeader
meth public java.lang.String getHeader(java.lang.String,java.lang.String)
meth public java.lang.String[] getHeader(java.lang.String)
meth public java.util.Enumeration getAllHeaderLines()
meth public java.util.Enumeration getAllHeaders()
meth public java.util.Enumeration getMatchingHeaderLines(java.lang.String[])
meth public java.util.Enumeration getMatchingHeaders(java.lang.String[])
meth public java.util.Enumeration getNonMatchingHeaderLines(java.lang.String[])
meth public java.util.Enumeration getNonMatchingHeaders(java.lang.String[])
meth public void addHeader(java.lang.String,java.lang.String)
meth public void addHeaderLine(java.lang.String)
meth public void load(java.io.InputStream) throws javax.mail.MessagingException
meth public void removeHeader(java.lang.String)
meth public void setHeader(java.lang.String,java.lang.String)
supr java.lang.Object
hfds ignoreWhitespaceLines
hcls matchEnum

CLSS protected final static javax.mail.internet.InternetHeaders$InternetHeader
cons public InternetHeader(java.lang.String)
cons public InternetHeader(java.lang.String,java.lang.String)
meth public java.lang.String getValue()
supr javax.mail.Header
hfds line

CLSS public javax.mail.internet.MailDateFormat
cons public MailDateFormat()
meth public java.lang.StringBuffer format(java.util.Date,java.lang.StringBuffer,java.text.FieldPosition)
meth public java.util.Date parse(java.lang.String,java.text.ParsePosition)
meth public void setCalendar(java.util.Calendar)
meth public void setNumberFormat(java.text.NumberFormat)
supr java.text.SimpleDateFormat
hfds cal,debug,serialVersionUID

CLSS public javax.mail.internet.MimeBodyPart
cons public MimeBodyPart()
cons public MimeBodyPart(java.io.InputStream) throws javax.mail.MessagingException
cons public MimeBodyPart(javax.mail.internet.InternetHeaders,byte[]) throws javax.mail.MessagingException
fld protected byte[] content
fld protected java.io.InputStream contentStream
fld protected javax.activation.DataHandler dh
fld protected javax.mail.internet.InternetHeaders headers
intf javax.mail.internet.MimePart
meth protected java.io.InputStream getContentStream() throws javax.mail.MessagingException
meth protected void updateHeaders() throws javax.mail.MessagingException
meth public boolean isMimeType(java.lang.String) throws javax.mail.MessagingException
meth public int getLineCount() throws javax.mail.MessagingException
meth public int getSize() throws javax.mail.MessagingException
meth public java.io.InputStream getInputStream() throws java.io.IOException,javax.mail.MessagingException
meth public java.io.InputStream getRawInputStream() throws javax.mail.MessagingException
meth public java.lang.Object getContent() throws java.io.IOException,javax.mail.MessagingException
meth public java.lang.String getContentID() throws javax.mail.MessagingException
meth public java.lang.String getContentMD5() throws javax.mail.MessagingException
meth public java.lang.String getContentType() throws javax.mail.MessagingException
meth public java.lang.String getDescription() throws javax.mail.MessagingException
meth public java.lang.String getDisposition() throws javax.mail.MessagingException
meth public java.lang.String getEncoding() throws javax.mail.MessagingException
meth public java.lang.String getFileName() throws javax.mail.MessagingException
meth public java.lang.String getHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String[] getContentLanguage() throws javax.mail.MessagingException
meth public java.lang.String[] getHeader(java.lang.String) throws javax.mail.MessagingException
meth public java.util.Enumeration getAllHeaderLines() throws javax.mail.MessagingException
meth public java.util.Enumeration getAllHeaders() throws javax.mail.MessagingException
meth public java.util.Enumeration getMatchingHeaderLines(java.lang.String[]) throws javax.mail.MessagingException
meth public java.util.Enumeration getMatchingHeaders(java.lang.String[]) throws javax.mail.MessagingException
meth public java.util.Enumeration getNonMatchingHeaderLines(java.lang.String[]) throws javax.mail.MessagingException
meth public java.util.Enumeration getNonMatchingHeaders(java.lang.String[]) throws javax.mail.MessagingException
meth public javax.activation.DataHandler getDataHandler() throws javax.mail.MessagingException
meth public void addHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void addHeaderLine(java.lang.String) throws javax.mail.MessagingException
meth public void attachFile(java.io.File) throws java.io.IOException,javax.mail.MessagingException
meth public void attachFile(java.lang.String) throws java.io.IOException,javax.mail.MessagingException
meth public void removeHeader(java.lang.String) throws javax.mail.MessagingException
meth public void saveFile(java.io.File) throws java.io.IOException,javax.mail.MessagingException
meth public void saveFile(java.lang.String) throws java.io.IOException,javax.mail.MessagingException
meth public void setContent(java.lang.Object,java.lang.String) throws javax.mail.MessagingException
meth public void setContent(javax.mail.Multipart) throws javax.mail.MessagingException
meth public void setContentID(java.lang.String) throws javax.mail.MessagingException
meth public void setContentLanguage(java.lang.String[]) throws javax.mail.MessagingException
meth public void setContentMD5(java.lang.String) throws javax.mail.MessagingException
meth public void setDataHandler(javax.activation.DataHandler) throws javax.mail.MessagingException
meth public void setDescription(java.lang.String) throws javax.mail.MessagingException
meth public void setDescription(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void setDisposition(java.lang.String) throws javax.mail.MessagingException
meth public void setFileName(java.lang.String) throws javax.mail.MessagingException
meth public void setHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void setText(java.lang.String) throws javax.mail.MessagingException
meth public void setText(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void setText(java.lang.String,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void writeTo(java.io.OutputStream) throws java.io.IOException,javax.mail.MessagingException
supr javax.mail.BodyPart
hfds cacheMultipart,cachedContent,decodeFileName,encodeFileName,ignoreMultipartEncoding,setContentTypeFileName,setDefaultTextCharset
hcls MimePartDataHandler

CLSS public javax.mail.internet.MimeMessage
cons protected MimeMessage(javax.mail.Folder,int)
cons protected MimeMessage(javax.mail.Folder,java.io.InputStream,int) throws javax.mail.MessagingException
cons protected MimeMessage(javax.mail.Folder,javax.mail.internet.InternetHeaders,byte[],int) throws javax.mail.MessagingException
cons public MimeMessage(javax.mail.Session)
cons public MimeMessage(javax.mail.Session,java.io.InputStream) throws javax.mail.MessagingException
cons public MimeMessage(javax.mail.internet.MimeMessage) throws javax.mail.MessagingException
fld protected boolean modified
fld protected boolean saved
fld protected byte[] content
fld protected java.io.InputStream contentStream
fld protected javax.activation.DataHandler dh
fld protected javax.mail.Flags flags
fld protected javax.mail.internet.InternetHeaders headers
innr public static RecipientType
intf javax.mail.internet.MimePart
meth protected java.io.InputStream getContentStream() throws javax.mail.MessagingException
meth protected javax.mail.internet.InternetHeaders createInternetHeaders(java.io.InputStream) throws javax.mail.MessagingException
meth protected javax.mail.internet.MimeMessage createMimeMessage(javax.mail.Session) throws javax.mail.MessagingException
meth protected void parse(java.io.InputStream) throws javax.mail.MessagingException
meth protected void updateHeaders() throws javax.mail.MessagingException
meth protected void updateMessageID() throws javax.mail.MessagingException
meth public boolean isMimeType(java.lang.String) throws javax.mail.MessagingException
meth public boolean isSet(javax.mail.Flags$Flag) throws javax.mail.MessagingException
meth public int getLineCount() throws javax.mail.MessagingException
meth public int getSize() throws javax.mail.MessagingException
meth public java.io.InputStream getInputStream() throws java.io.IOException,javax.mail.MessagingException
meth public java.io.InputStream getRawInputStream() throws javax.mail.MessagingException
meth public java.lang.Object getContent() throws java.io.IOException,javax.mail.MessagingException
meth public java.lang.String getContentID() throws javax.mail.MessagingException
meth public java.lang.String getContentMD5() throws javax.mail.MessagingException
meth public java.lang.String getContentType() throws javax.mail.MessagingException
meth public java.lang.String getDescription() throws javax.mail.MessagingException
meth public java.lang.String getDisposition() throws javax.mail.MessagingException
meth public java.lang.String getEncoding() throws javax.mail.MessagingException
meth public java.lang.String getFileName() throws javax.mail.MessagingException
meth public java.lang.String getHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public java.lang.String getMessageID() throws javax.mail.MessagingException
meth public java.lang.String getSubject() throws javax.mail.MessagingException
meth public java.lang.String[] getContentLanguage() throws javax.mail.MessagingException
meth public java.lang.String[] getHeader(java.lang.String) throws javax.mail.MessagingException
meth public java.util.Date getReceivedDate() throws javax.mail.MessagingException
meth public java.util.Date getSentDate() throws javax.mail.MessagingException
meth public java.util.Enumeration getAllHeaderLines() throws javax.mail.MessagingException
meth public java.util.Enumeration getAllHeaders() throws javax.mail.MessagingException
meth public java.util.Enumeration getMatchingHeaderLines(java.lang.String[]) throws javax.mail.MessagingException
meth public java.util.Enumeration getMatchingHeaders(java.lang.String[]) throws javax.mail.MessagingException
meth public java.util.Enumeration getNonMatchingHeaderLines(java.lang.String[]) throws javax.mail.MessagingException
meth public java.util.Enumeration getNonMatchingHeaders(java.lang.String[]) throws javax.mail.MessagingException
meth public javax.activation.DataHandler getDataHandler() throws javax.mail.MessagingException
meth public javax.mail.Address getSender() throws javax.mail.MessagingException
meth public javax.mail.Address[] getAllRecipients() throws javax.mail.MessagingException
meth public javax.mail.Address[] getFrom() throws javax.mail.MessagingException
meth public javax.mail.Address[] getRecipients(javax.mail.Message$RecipientType) throws javax.mail.MessagingException
meth public javax.mail.Address[] getReplyTo() throws javax.mail.MessagingException
meth public javax.mail.Flags getFlags() throws javax.mail.MessagingException
meth public javax.mail.Message reply(boolean) throws javax.mail.MessagingException
meth public void addFrom(javax.mail.Address[]) throws javax.mail.MessagingException
meth public void addHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void addHeaderLine(java.lang.String) throws javax.mail.MessagingException
meth public void addRecipients(javax.mail.Message$RecipientType,java.lang.String) throws javax.mail.MessagingException
meth public void addRecipients(javax.mail.Message$RecipientType,javax.mail.Address[]) throws javax.mail.MessagingException
meth public void removeHeader(java.lang.String) throws javax.mail.MessagingException
meth public void saveChanges() throws javax.mail.MessagingException
meth public void setContent(java.lang.Object,java.lang.String) throws javax.mail.MessagingException
meth public void setContent(javax.mail.Multipart) throws javax.mail.MessagingException
meth public void setContentID(java.lang.String) throws javax.mail.MessagingException
meth public void setContentLanguage(java.lang.String[]) throws javax.mail.MessagingException
meth public void setContentMD5(java.lang.String) throws javax.mail.MessagingException
meth public void setDataHandler(javax.activation.DataHandler) throws javax.mail.MessagingException
meth public void setDescription(java.lang.String) throws javax.mail.MessagingException
meth public void setDescription(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void setDisposition(java.lang.String) throws javax.mail.MessagingException
meth public void setFileName(java.lang.String) throws javax.mail.MessagingException
meth public void setFlags(javax.mail.Flags,boolean) throws javax.mail.MessagingException
meth public void setFrom() throws javax.mail.MessagingException
meth public void setFrom(javax.mail.Address) throws javax.mail.MessagingException
meth public void setHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void setRecipients(javax.mail.Message$RecipientType,java.lang.String) throws javax.mail.MessagingException
meth public void setRecipients(javax.mail.Message$RecipientType,javax.mail.Address[]) throws javax.mail.MessagingException
meth public void setReplyTo(javax.mail.Address[]) throws javax.mail.MessagingException
meth public void setSender(javax.mail.Address) throws javax.mail.MessagingException
meth public void setSentDate(java.util.Date) throws javax.mail.MessagingException
meth public void setSubject(java.lang.String) throws javax.mail.MessagingException
meth public void setSubject(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void setText(java.lang.String) throws javax.mail.MessagingException
meth public void setText(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void setText(java.lang.String,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public void writeTo(java.io.OutputStream) throws java.io.IOException,javax.mail.MessagingException
meth public void writeTo(java.io.OutputStream,java.lang.String[]) throws java.io.IOException,javax.mail.MessagingException
supr javax.mail.Message
hfds answeredFlag,cachedContent,mailDateFormat,strict

CLSS public static javax.mail.internet.MimeMessage$RecipientType
cons protected RecipientType(java.lang.String)
fld public final static javax.mail.internet.MimeMessage$RecipientType NEWSGROUPS
meth protected java.lang.Object readResolve() throws java.io.ObjectStreamException
supr javax.mail.Message$RecipientType
hfds serialVersionUID

CLSS public javax.mail.internet.MimeMultipart
cons public MimeMultipart()
cons public MimeMultipart(java.lang.String)
cons public MimeMultipart(javax.activation.DataSource) throws javax.mail.MessagingException
fld protected boolean parsed
fld protected javax.activation.DataSource ds
meth protected javax.mail.internet.InternetHeaders createInternetHeaders(java.io.InputStream) throws javax.mail.MessagingException
meth protected javax.mail.internet.MimeBodyPart createMimeBodyPart(java.io.InputStream) throws javax.mail.MessagingException
meth protected javax.mail.internet.MimeBodyPart createMimeBodyPart(javax.mail.internet.InternetHeaders,byte[]) throws javax.mail.MessagingException
meth protected void parse() throws javax.mail.MessagingException
meth protected void updateHeaders() throws javax.mail.MessagingException
meth public boolean isComplete() throws javax.mail.MessagingException
meth public boolean removeBodyPart(javax.mail.BodyPart) throws javax.mail.MessagingException
meth public int getCount() throws javax.mail.MessagingException
meth public java.lang.String getPreamble() throws javax.mail.MessagingException
meth public javax.mail.BodyPart getBodyPart(int) throws javax.mail.MessagingException
meth public javax.mail.BodyPart getBodyPart(java.lang.String) throws javax.mail.MessagingException
meth public void addBodyPart(javax.mail.BodyPart) throws javax.mail.MessagingException
meth public void addBodyPart(javax.mail.BodyPart,int) throws javax.mail.MessagingException
meth public void removeBodyPart(int) throws javax.mail.MessagingException
meth public void setPreamble(java.lang.String) throws javax.mail.MessagingException
meth public void setSubType(java.lang.String) throws javax.mail.MessagingException
meth public void writeTo(java.io.OutputStream) throws java.io.IOException,javax.mail.MessagingException
supr javax.mail.Multipart
hfds allowEmpty,bmparse,complete,ignoreExistingBoundaryParameter,ignoreMissingBoundaryParameter,ignoreMissingEndBoundary,preamble

CLSS public abstract interface javax.mail.internet.MimePart
intf javax.mail.Part
meth public abstract java.lang.String getContentID() throws javax.mail.MessagingException
meth public abstract java.lang.String getContentMD5() throws javax.mail.MessagingException
meth public abstract java.lang.String getEncoding() throws javax.mail.MessagingException
meth public abstract java.lang.String getHeader(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract java.lang.String[] getContentLanguage() throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getAllHeaderLines() throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getMatchingHeaderLines(java.lang.String[]) throws javax.mail.MessagingException
meth public abstract java.util.Enumeration getNonMatchingHeaderLines(java.lang.String[]) throws javax.mail.MessagingException
meth public abstract void addHeaderLine(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setContentLanguage(java.lang.String[]) throws javax.mail.MessagingException
meth public abstract void setContentMD5(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public abstract void setText(java.lang.String,java.lang.String,java.lang.String) throws javax.mail.MessagingException

CLSS public javax.mail.internet.MimePartDataSource
cons public MimePartDataSource(javax.mail.internet.MimePart)
fld protected javax.mail.internet.MimePart part
intf javax.activation.DataSource
intf javax.mail.MessageAware
meth public java.io.InputStream getInputStream() throws java.io.IOException
meth public java.io.OutputStream getOutputStream() throws java.io.IOException
meth public java.lang.String getContentType()
meth public java.lang.String getName()
meth public javax.mail.MessageContext getMessageContext()
supr java.lang.Object
hfds context

CLSS public javax.mail.internet.MimeUtility
fld public final static int ALL = -1
meth public static java.io.InputStream decode(java.io.InputStream,java.lang.String) throws javax.mail.MessagingException
meth public static java.io.OutputStream encode(java.io.OutputStream,java.lang.String) throws javax.mail.MessagingException
meth public static java.io.OutputStream encode(java.io.OutputStream,java.lang.String,java.lang.String) throws javax.mail.MessagingException
meth public static java.lang.String decodeText(java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String decodeWord(java.lang.String) throws java.io.UnsupportedEncodingException,javax.mail.internet.ParseException
meth public static java.lang.String encodeText(java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String encodeText(java.lang.String,java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String encodeWord(java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String encodeWord(java.lang.String,java.lang.String,java.lang.String) throws java.io.UnsupportedEncodingException
meth public static java.lang.String fold(int,java.lang.String)
meth public static java.lang.String getDefaultJavaCharset()
meth public static java.lang.String getEncoding(javax.activation.DataHandler)
meth public static java.lang.String getEncoding(javax.activation.DataSource)
meth public static java.lang.String javaCharset(java.lang.String)
meth public static java.lang.String mimeCharset(java.lang.String)
meth public static java.lang.String quote(java.lang.String,java.lang.String)
meth public static java.lang.String unfold(java.lang.String)
supr java.lang.Object
hfds ALL_ASCII,MOSTLY_ASCII,MOSTLY_NONASCII,class$javax$mail$internet$MimeUtility,decodeStrict,defaultJavaCharset,defaultMIMECharset,encodeEolStrict,foldEncodedWords,foldText,ignoreUnknownEncoding,java2mime,mime2java,nonAsciiCharsetMap

CLSS public javax.mail.internet.NewsAddress
cons public NewsAddress()
cons public NewsAddress(java.lang.String)
cons public NewsAddress(java.lang.String,java.lang.String)
fld protected java.lang.String host
fld protected java.lang.String newsgroup
meth public boolean equals(java.lang.Object)
meth public int hashCode()
meth public java.lang.String getHost()
meth public java.lang.String getNewsgroup()
meth public java.lang.String getType()
meth public java.lang.String toString()
meth public static java.lang.String toString(javax.mail.Address[])
meth public static javax.mail.internet.NewsAddress[] parse(java.lang.String) throws javax.mail.internet.AddressException
meth public void setHost(java.lang.String)
meth public void setNewsgroup(java.lang.String)
supr javax.mail.Address
hfds serialVersionUID

CLSS public javax.mail.internet.ParameterList
cons public ParameterList()
cons public ParameterList(java.lang.String) throws javax.mail.internet.ParseException
meth public int size()
meth public java.lang.String get(java.lang.String)
meth public java.lang.String toString()
meth public java.lang.String toString(int)
meth public java.util.Enumeration getNames()
meth public void remove(java.lang.String)
meth public void set(java.lang.String,java.lang.String)
meth public void set(java.lang.String,java.lang.String,java.lang.String)
supr java.lang.Object
hfds applehack,decodeParameters,decodeParametersStrict,encodeParameters,hex,lastName,list,multisegmentNames,parametersStrict,slist,windowshack
hcls 1,MultiValue,ParamEnum,ToStringBuffer,Value

CLSS public javax.mail.internet.ParseException
cons public ParseException()
cons public ParseException(java.lang.String)
supr javax.mail.MessagingException
hfds serialVersionUID

CLSS public javax.mail.internet.PreencodedMimeBodyPart
cons public PreencodedMimeBodyPart(java.lang.String)
meth protected void updateHeaders() throws javax.mail.MessagingException
meth public java.lang.String getEncoding() throws javax.mail.MessagingException
meth public void writeTo(java.io.OutputStream) throws java.io.IOException,javax.mail.MessagingException
supr javax.mail.internet.MimeBodyPart
hfds encoding

CLSS public abstract interface javax.mail.internet.SharedInputStream
meth public abstract java.io.InputStream newStream(long,long)
meth public abstract long getPosition()

CLSS public abstract javax.mail.search.AddressStringTerm
cons protected AddressStringTerm(java.lang.String)
meth protected boolean match(javax.mail.Address)
meth public boolean equals(java.lang.Object)
supr javax.mail.search.StringTerm
hfds serialVersionUID

CLSS public abstract javax.mail.search.AddressTerm
cons protected AddressTerm(javax.mail.Address)
fld protected javax.mail.Address address
meth protected boolean match(javax.mail.Address)
meth public boolean equals(java.lang.Object)
meth public int hashCode()
meth public javax.mail.Address getAddress()
supr javax.mail.search.SearchTerm
hfds serialVersionUID

CLSS public final javax.mail.search.AndTerm
cons public AndTerm(javax.mail.search.SearchTerm,javax.mail.search.SearchTerm)
cons public AndTerm(javax.mail.search.SearchTerm[])
fld protected javax.mail.search.SearchTerm[] terms
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
meth public int hashCode()
meth public javax.mail.search.SearchTerm[] getTerms()
supr javax.mail.search.SearchTerm
hfds serialVersionUID

CLSS public final javax.mail.search.BodyTerm
cons public BodyTerm(java.lang.String)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
supr javax.mail.search.StringTerm
hfds serialVersionUID

CLSS public abstract javax.mail.search.ComparisonTerm
cons public ComparisonTerm()
fld protected int comparison
fld public final static int EQ = 3
fld public final static int GE = 6
fld public final static int GT = 5
fld public final static int LE = 1
fld public final static int LT = 2
fld public final static int NE = 4
meth public boolean equals(java.lang.Object)
meth public int hashCode()
supr javax.mail.search.SearchTerm
hfds serialVersionUID

CLSS public abstract javax.mail.search.DateTerm
cons protected DateTerm(int,java.util.Date)
fld protected java.util.Date date
meth protected boolean match(java.util.Date)
meth public boolean equals(java.lang.Object)
meth public int getComparison()
meth public int hashCode()
meth public java.util.Date getDate()
supr javax.mail.search.ComparisonTerm
hfds serialVersionUID

CLSS public final javax.mail.search.FlagTerm
cons public FlagTerm(javax.mail.Flags,boolean)
fld protected boolean set
fld protected javax.mail.Flags flags
meth public boolean equals(java.lang.Object)
meth public boolean getTestSet()
meth public boolean match(javax.mail.Message)
meth public int hashCode()
meth public javax.mail.Flags getFlags()
supr javax.mail.search.SearchTerm
hfds serialVersionUID

CLSS public final javax.mail.search.FromStringTerm
cons public FromStringTerm(java.lang.String)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
supr javax.mail.search.AddressStringTerm
hfds serialVersionUID

CLSS public final javax.mail.search.FromTerm
cons public FromTerm(javax.mail.Address)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
supr javax.mail.search.AddressTerm
hfds serialVersionUID

CLSS public final javax.mail.search.HeaderTerm
cons public HeaderTerm(java.lang.String,java.lang.String)
fld protected java.lang.String headerName
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
meth public int hashCode()
meth public java.lang.String getHeaderName()
supr javax.mail.search.StringTerm
hfds serialVersionUID

CLSS public abstract javax.mail.search.IntegerComparisonTerm
cons protected IntegerComparisonTerm(int,int)
fld protected int number
meth protected boolean match(int)
meth public boolean equals(java.lang.Object)
meth public int getComparison()
meth public int getNumber()
meth public int hashCode()
supr javax.mail.search.ComparisonTerm
hfds serialVersionUID

CLSS public final javax.mail.search.MessageIDTerm
cons public MessageIDTerm(java.lang.String)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
supr javax.mail.search.StringTerm
hfds serialVersionUID

CLSS public final javax.mail.search.MessageNumberTerm
cons public MessageNumberTerm(int)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
supr javax.mail.search.IntegerComparisonTerm
hfds serialVersionUID

CLSS public final javax.mail.search.NotTerm
cons public NotTerm(javax.mail.search.SearchTerm)
fld protected javax.mail.search.SearchTerm term
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
meth public int hashCode()
meth public javax.mail.search.SearchTerm getTerm()
supr javax.mail.search.SearchTerm
hfds serialVersionUID

CLSS public final javax.mail.search.OrTerm
cons public OrTerm(javax.mail.search.SearchTerm,javax.mail.search.SearchTerm)
cons public OrTerm(javax.mail.search.SearchTerm[])
fld protected javax.mail.search.SearchTerm[] terms
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
meth public int hashCode()
meth public javax.mail.search.SearchTerm[] getTerms()
supr javax.mail.search.SearchTerm
hfds serialVersionUID

CLSS public final javax.mail.search.ReceivedDateTerm
cons public ReceivedDateTerm(int,java.util.Date)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
supr javax.mail.search.DateTerm
hfds serialVersionUID

CLSS public final javax.mail.search.RecipientStringTerm
cons public RecipientStringTerm(javax.mail.Message$RecipientType,java.lang.String)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
meth public int hashCode()
meth public javax.mail.Message$RecipientType getRecipientType()
supr javax.mail.search.AddressStringTerm
hfds serialVersionUID,type

CLSS public final javax.mail.search.RecipientTerm
cons public RecipientTerm(javax.mail.Message$RecipientType,javax.mail.Address)
fld protected javax.mail.Message$RecipientType type
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
meth public int hashCode()
meth public javax.mail.Message$RecipientType getRecipientType()
supr javax.mail.search.AddressTerm
hfds serialVersionUID

CLSS public javax.mail.search.SearchException
cons public SearchException()
cons public SearchException(java.lang.String)
supr javax.mail.MessagingException
hfds serialVersionUID

CLSS public abstract javax.mail.search.SearchTerm
cons public SearchTerm()
intf java.io.Serializable
meth public abstract boolean match(javax.mail.Message)
supr java.lang.Object
hfds serialVersionUID

CLSS public final javax.mail.search.SentDateTerm
cons public SentDateTerm(int,java.util.Date)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
supr javax.mail.search.DateTerm
hfds serialVersionUID

CLSS public final javax.mail.search.SizeTerm
cons public SizeTerm(int,int)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
supr javax.mail.search.IntegerComparisonTerm
hfds serialVersionUID

CLSS public abstract javax.mail.search.StringTerm
cons protected StringTerm(java.lang.String)
cons protected StringTerm(java.lang.String,boolean)
fld protected boolean ignoreCase
fld protected java.lang.String pattern
meth protected boolean match(java.lang.String)
meth public boolean equals(java.lang.Object)
meth public boolean getIgnoreCase()
meth public int hashCode()
meth public java.lang.String getPattern()
supr javax.mail.search.SearchTerm
hfds serialVersionUID

CLSS public final javax.mail.search.SubjectTerm
cons public SubjectTerm(java.lang.String)
meth public boolean equals(java.lang.Object)
meth public boolean match(javax.mail.Message)
supr javax.mail.search.StringTerm
hfds serialVersionUID

CLSS public javax.mail.util.ByteArrayDataSource
cons public ByteArrayDataSource(byte[],java.lang.String)
cons public ByteArrayDataSource(java.io.InputStream,java.lang.String) throws java.io.IOException
cons public ByteArrayDataSource(java.lang.String,java.lang.String) throws java.io.IOException
intf javax.activation.DataSource
meth public java.io.InputStream getInputStream() throws java.io.IOException
meth public java.io.OutputStream getOutputStream() throws java.io.IOException
meth public java.lang.String getContentType()
meth public java.lang.String getName()
meth public void setName(java.lang.String)
supr java.lang.Object
hfds data,len,name,type
hcls DSByteArrayOutputStream

CLSS public javax.mail.util.SharedByteArrayInputStream
cons public SharedByteArrayInputStream(byte[])
cons public SharedByteArrayInputStream(byte[],int,int)
fld protected int start
intf javax.mail.internet.SharedInputStream
meth public java.io.InputStream newStream(long,long)
meth public long getPosition()
supr java.io.ByteArrayInputStream

CLSS public javax.mail.util.SharedFileInputStream
cons public SharedFileInputStream(java.io.File) throws java.io.IOException
cons public SharedFileInputStream(java.io.File,int) throws java.io.IOException
cons public SharedFileInputStream(java.lang.String) throws java.io.IOException
cons public SharedFileInputStream(java.lang.String,int) throws java.io.IOException
fld protected int bufsize
fld protected java.io.RandomAccessFile in
fld protected long bufpos
fld protected long datalen
fld protected long start
intf javax.mail.internet.SharedInputStream
meth protected void finalize() throws java.lang.Throwable
meth public boolean markSupported()
meth public int available() throws java.io.IOException
meth public int read() throws java.io.IOException
meth public int read(byte[],int,int) throws java.io.IOException
meth public java.io.InputStream newStream(long,long)
meth public long getPosition()
meth public long skip(long) throws java.io.IOException
meth public void close() throws java.io.IOException
meth public void mark(int)
meth public void reset() throws java.io.IOException
supr java.io.BufferedInputStream
hfds defaultBufferSize,master,sf
hcls SharedFile

