package datastructures;

import java.util.Objects;
/**
 * A basic HashMap implementation using separate chaining.
 * @author ShaheerZK
 */
public class HashMap<K, V>
{
    private static final int DEFAULT_CAPACITY = 100;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private ArrayList<Entry<K, V>>[] buckets;
    private int size;
    private int capacity;
    private final float loadFactor;

    private class Entry<K, V>
    {
        private final K key;
        private V value;

        Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
    public HashMap()
    {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    public HashMap(int initialCapacity)
    {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    public HashMap(int initialCapacity, float loadFactor)
    {
        if (initialCapacity <= 0)
            initialCapacity = DEFAULT_CAPACITY;
        if (loadFactor <= 0)
            loadFactor = DEFAULT_LOAD_FACTOR;

        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.buckets = createBucketArray(this.capacity);
    }
    private ArrayList<Entry<K, V>>[] createBucketArray(int capacity)
    {
        return (ArrayList<Entry<K, V>>[]) new ArrayList[capacity];
    }
    private int getBucketIndex(K key)
    {
        if (key == null)
            return 0;
        int hash = key.hashCode();
        return Math.abs(hash) % capacity;
    }
    /**
     * Inserts the value in the HashMap and returns the old value if a collision occurs.
     * @param key
     * @param value
     * @return 
     */
    public V put(K key, V value)
    {
        int index = getBucketIndex(key);

        if (buckets[index] == null)
            buckets[index] = new ArrayList<>();

        ArrayList<Entry<K, V>> bucket = buckets[index];
        for (int i = 0; i < bucket.size(); i++)
        {
            Entry<K, V> entry = bucket.get(i);
            if (Objects.equals(entry.key, key))
            {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        
        bucket.add(new Entry<>(key, value));
        size++;

        if ((float) size / capacity >= loadFactor)
            resize(capacity * 2);

        return null;
    }
    /**
     * Returns the value if HashMap contains the key, else it'd return null.
     * @param key
     * @return 
     */
    public V get(K key)
    {
        int index = getBucketIndex(key);
        ArrayList<Entry<K, V>> bucket = buckets[index];
        if (bucket == null)
            return null;
        for (int i = 0; i < bucket.size(); i++)
        {
            Entry<K, V> entry = bucket.get(i);
            if (Objects.equals(entry.key, key))
                return entry.value;
        }
        return null;
    }
    /**
     * Returns whether the HashMap contains the provided key.
     * @param key
     * @return 
     */
    public boolean containsKey(K key)
    {
        int index = getBucketIndex(key);
        ArrayList<Entry<K, V>> bucket = buckets[index];
        if (bucket == null)
            return false;

        for (int i = 0; i < bucket.size(); i++)
        {
            if (Objects.equals(bucket.get(i).key, key))
                return true;
        }

        return false;
    }
    /**
     * Returns and removes the value if the key exists, else it'd return null if the key doesn't exist.
     * @param key
     * @return 
     */
    public V remove(K key)
    {
        int index = getBucketIndex(key);
        ArrayList<Entry<K, V>> bucket = buckets[index];
        if (bucket == null)
            return null;

        for (int i = 0; i < bucket.size(); i++)
        {
            Entry<K, V> entry = bucket.get(i);
            if (Objects.equals(entry.key, key))
            {
                V oldValue = entry.value;
                bucket.remove(i);
                size--;
                return oldValue;
            }
        }
        return null;
    }
    /**
     * Clears the whole HashMap.
     */
    public void clear()
    {
        this.buckets = createBucketArray(capacity);
        this.size = 0;
    }
    /**
     * Returns the size of the HashMap.
     * @return 
     */
    public int size()
    {
        return size;
    }
    /**
     * Returns whether the HashMap is empty.
     * @return 
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    private void resize(int newCapacity)
    {
        ArrayList<Entry<K, V>>[] oldBuckets = buckets;

        this.capacity = newCapacity;
        this.buckets = createBucketArray(newCapacity);
        this.size = 0;

        for (ArrayList<Entry<K, V>> oldBucket : oldBuckets) 
        {
            if (oldBucket == null)
                continue;
            for (int j = 0; j < oldBucket.size(); j++) 
            {
                Entry<K, V> entry = oldBucket.get(j);
                put(entry.key, entry.value);
            }
        }
    }
}
