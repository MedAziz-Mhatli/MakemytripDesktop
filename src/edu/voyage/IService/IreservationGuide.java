/* Decompiler 0ms, total 166ms, lines 12 */
package edu.voyage.IService;

import voyage.services.*;
import voyage.entities.reservation_guide;

public interface IreservationGuide<t> {
   void insert(reservation_guide t);

   void update(reservation_guide t, int id);

   void delete(int id);
}
