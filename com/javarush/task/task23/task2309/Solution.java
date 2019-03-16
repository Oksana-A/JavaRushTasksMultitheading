package com.javarush.task.task23.task2309;

import com.javarush.task.task23.task2309.vo.*;

import java.util.List;

/* 
Анонимность иногда так приятна!
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.getUsers());
        print(solution.getLocations());
    }

    public static void print(List list) {
        String format = "Id=%d, name='%s', description=%s";
        for (Object obj : list) {
            NamedItem item = (NamedItem) obj;
            System.out.println(String.format(format, item.getId(), item.getName(), item.getDescription()));
        }
    }

    public List<User> getUsers() {
        AbstractDbSelectExecutor<User> abs = new AbstractDbSelectExecutor<User>() {
            public String getQuery() {
                return "SELECT * FROM USER";
            }
        };
        return abs.execute();

    }
    public List<Subscription> getSubscriptions() {
        AbstractDbSelectExecutor<Subscription> abs = new AbstractDbSelectExecutor<Subscription>() {
            public String getQuery() {
                return "SELECT * FROM SUBSCRIPTION";
            }
        };
        return abs.execute();
    }
    public List<Subject> getSubjects() {
        AbstractDbSelectExecutor<Subject> abs = new AbstractDbSelectExecutor<Subject>() {
            public String getQuery() {
                return "SELECT * FROM SUBJECT";
            }
        };
        return abs.execute();
    }
    public List<Server> getServers() {
        AbstractDbSelectExecutor<Server> abs = new AbstractDbSelectExecutor<Server>() {
            public String getQuery() {
                return "SELECT * FROM SERVER";
            }
        };
        return abs.execute();
    }
    public List<Location> getLocations() {
        AbstractDbSelectExecutor<Location> abs = new AbstractDbSelectExecutor<Location>() {
            public String getQuery() {
                return "SELECT * FROM LOCATION";
            }
        };
        return abs.execute();
    }
}
