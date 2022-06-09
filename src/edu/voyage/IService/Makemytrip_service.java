/* Decompiler 0ms, total 163ms, lines 18 */
package edu.voyage.IService;

import java.util.List;

public interface Makemytrip_service<T> {
   void insert(T t) throws Exception;

   void update(T t, int id);

   void delete(int id);

   void update(T t);

   List<T> read();

   T readById(int id);
}
