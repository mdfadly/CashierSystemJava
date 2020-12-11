package Restaurant;

public interface RestaurantService {
	public void orderanMenu(String menu, String jumlahOrder);
	public int getHargaMenu(String menu, String Harga);
	public void receipt();
}
