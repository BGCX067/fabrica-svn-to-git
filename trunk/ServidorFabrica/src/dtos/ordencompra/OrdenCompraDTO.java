package dtos.ordencompra;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;




public class OrdenCompraDTO {
	
		private long ID;
		private Date fecha;
		private Set<OrdenCompraItemDTO> items = new HashSet<OrdenCompraItemDTO>();
		
		public Date getFecha() {
			return fecha;
		}
		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
		public long getID() {
			return ID;
		}
		public void setID(long id) {
			ID = id;
		}
		public Set<OrdenCompraItemDTO> getItems() {
			return items;
		}
		public void setItems(Set<OrdenCompraItemDTO> items) {
			this.items = items;
		}

		
		
		
}
