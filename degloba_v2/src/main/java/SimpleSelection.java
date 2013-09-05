

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.richfaces.application.push.MessageData;
import org.richfaces.application.push.Request;
import org.richfaces.application.push.Session;
import org.richfaces.application.push.TopicKey;


import com.google.common.collect.Multimap;


public class SimpleSelection implements Session {

 	private static final long serialVersionUID = 1L;
 
 	private Set<Object> keys = new LinkedHashSet<Object>();
 	
 	private boolean selectAll = false;
 	
 	
 	private String title;
 	private String body;
 
 	public boolean addKey(Object rowKey) {
 		return keys.add(rowKey);
 	}
 	
 	public boolean removeKey(Object rowKey) {
 		selectAll = false;
 		return keys.remove(rowKey);
 	}
 	
 	public Iterator<Object> getKeys() {
 		Iterator<Object> result = Collections.emptyList().iterator();;
 		if (!selectAll) {
		result = keys.iterator();
 		}
 		return result;
 	}
 
 	public int size() {
 		int result = -1;
 		if (!selectAll) {
 			result = keys.size();
 		}
 		return result;
 	}
 	
 	public boolean isSelected(Object rowKey) {
 		return selectAll || keys.contains(rowKey);
 	}
 
 	public void clear() {
 		selectAll = false;
 		keys.clear();
 	}
 
 	public int hashCode() {
 		final int prime = 31;
 		int result = 1;
 		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
 		result = prime * result + (selectAll ? 1231 : 1237);
 		return result;
 	}
 
 	public boolean equals(Object obj) {
 		if (this == obj)
 			return true;
 		if (obj == null)
 			return false;
 		if (getClass() != obj.getClass())
 			return false;
 		SimpleSelection other = (SimpleSelection) obj;
 		if (keys == null) {
 			if (other.keys != null)
 				return false;
 		} else if (!keys.equals(other.keys))
 			return false;
 		if (selectAll != other.selectAll)
 			return false;
 		return true;
 	}
 
	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public boolean isSelectAll() {
		return selectAll;
	}

	
	/***  Getters/Setters **********/
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void connect(Request arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void disconnect() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Map<TopicKey, String> getFailedSubscriptions() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return 0;
	}

/*	public Multimap<TopicKey, TopicKey> getSuccessfulSubscriptions() {
		// TODO Auto-generated method stub
		return null;
	}*/

	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

	public void subscribe(String[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearBroadcastedMessages(long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<MessageData> getMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(TopicKey arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<TopicKey> getSuccessfulSubscriptions() {
		// TODO Auto-generated method stub
		return null;
	}


	


	
	
}