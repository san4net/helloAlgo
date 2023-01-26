package org.design;

import java.util.*;

import com.google.common.collect.Maps;


public class Twitter {
    private Map<Integer, User> userIds = Maps.newConcurrentMap();

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        getUser(userId).postTweet(tweetId);
    }

    private User getUser(int userId) {
        if (userIds.get(userId) == null) {
            userIds.put(userId, new User(userId));
        }
        return userIds.get(userId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        User u = getUser(userId);

        Collection<Integer> follower = u.getfollowee();

        PriorityQueue<TweetNode> tweetsPriority = new PriorityQueue<>(userIds.size(),
                Comparator.reverseOrder()
//        		(a, b)->{
//					return Comparator.reverseOrder();
//        			//return a.getTimestamp()>b.getTimestamp() ? -1 : a.getTimestamp()==b.getTimestamp()?0:1;
//        			}
        );


        for (Integer id : follower) {
            tweetsPriority.add(getUser(id).getHead());
        }

        List<Integer> tweetList = new ArrayList<>();

        while (tweetsPriority.isEmpty() && tweetList.size() < 10) {
            TweetNode temp = tweetsPriority.poll();
            tweetList.add(temp.getTweetId());
            if (temp.getNext() != null) {
                tweetsPriority.add(temp.getNext());
            }
        }

        return tweetList;

    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        User u = getUser(followerId);
        if (u != null) {
            u.follow(followeeId);
        }
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        User u = getUser(followerId);
        if (u != null) {
            u.unfollow(followeeId);
        }
    }
    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */
	public static void main(String[] args) {
		Twitter twitter = new Twitter();
		twitter.postTweet(1,5);
        System.out.println(twitter.getNewsFeed(1));
		twitter.follow(1,2);
		twitter.postTweet(2,6);
        System.out.println(twitter.getNewsFeed(1));

	}
}



class User {
    // this is user following
    private Set<Integer> followee = new HashSet();
    private TweetNode head;
    private final int userId;

    User(int userId) {
        this.userId = userId;
        this.followee.add(userId);
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int tweetId) {
        head = new TweetNode(tweetId, System.currentTimeMillis(), head);
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followeeId) {
        followee.add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followeeId) {
        followee.remove(followeeId);
    }

    public Collection<Integer> getfollowee() {
        return Collections.unmodifiableCollection(followee);
    }

    public TweetNode getHead() {
        return head;
    }
}

class TweetNode implements Comparable<TweetNode> {
    private int tweetId;
    private long timestamp;
    private TweetNode next;

    public TweetNode(int tweetId, long timestamp, TweetNode next) {
        super();
        this.tweetId = tweetId;
        this.timestamp = timestamp;
        this.next = next;
    }

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public TweetNode getNext() {
        return next;
    }

    // comparing by timestamp
    @Override
    public int compareTo(TweetNode o) {
        return Long.compare(timestamp, o.getTimestamp());
    }
}



