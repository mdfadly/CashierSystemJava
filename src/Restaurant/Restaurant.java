package Restaurant;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Restaurant implements RestaurantService {

	Scanner input = new Scanner(System.in);
	private String restaurantName = "Safana Catering";
	private String restaurantAddress = "Jl. Belitung";
	private int order;

	LinkedHashMap<String, String> menuPaket = new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> detailPaket = new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> menuItem = new LinkedHashMap<String, String>();

	LinkedHashMap<String, String> orderFood = new LinkedHashMap<String, String>();

	public Restaurant() {
		super();
		menuPaket.put("Paket A", "35000");
		menuPaket.put("Paket B", "25000");
		menuPaket.put("Paket C", "15000");
		detailPaket.put("Paket A", "Nasi Uduk + Ayam Goreng + Mie + Telur + Es Teh Manis");
		detailPaket.put("Paket B", "Nasi Uduk + Ayam Goreng + Mie + Air Putih");
		detailPaket.put("Paket C", "Nasi Uduk + Mie + Telur");
		menuItem.put("Nasi Uduk", "7000");
		menuItem.put("Ayam Goreng", "10000");
		menuItem.put("Mie", "5000");
		menuItem.put("Telur", "5000");
		menuItem.put("Es Teh Manis", "5000");
		menuItem.put("Air Putih", "2000");
	}

	
	public String getRestaurantName() {
		return restaurantName;
	}


	public String getRestaurantAddress() {
		return restaurantAddress;
	}


	public void menuPaket() {
		System.out.println("----------");
		System.out.println("Menu Paket");
		System.out.println("----------");
		for (String i : menuPaket.keySet()) {
			System.out.println(i + ", Harga " + menuPaket.get(i));
			tampilDetailPaket(i);
			System.out.println("***********");
		}
		System.out.println("----------");
	}

	public void tampilDetailPaket(String key) {
		System.out.println(detailPaket.get(key));
	}

	public void menuItem() {
		System.out.println("-------------");
		System.out.println("Menu Per Item");
		System.out.println("-------------");
		for (String i : menuItem.keySet()) {
			System.out.println(i + ", Harga: " + menuItem.get(i));
		}
		System.out.println("-------------");
	}
	
	public int cekMenu(String key) {
		int jumlahOrder = 0;
		for (String i : orderFood.keySet()) {
			if(i.equals(key)) {
				jumlahOrder = Integer.parseInt(orderFood.get(i));							
			}
	}
		return jumlahOrder;
	}
	
	@Override
	public int getHargaMenu(String namaPaket, String key) {
		int harga = 0;
		if(namaPaket.equals("paket")) {
			harga = Integer.parseInt(menuPaket.get(key));			
		}else {
			harga = Integer.parseInt(menuItem.get(key));		
		}
		return harga;
	}
	
	@Override
	public void orderanMenu(String menu, String jumlahOrder) {
		// TODO Auto-generated method stub
		this.orderFood.put(menu, jumlahOrder);
	}
	
	@Override
	public void receipt() {
		// TODO Auto-generated method stub
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		System.out.println();
		System.out.println("===============================");
		System.out.println("        Pesanan Anda");
		System.out.println("waktu: "+ formattedDate);
		System.out.println("===============================");
		System.out.println();
		int harga = 0;
		int total = 0;
		for (String i : orderFood.keySet()) {
			System.out.print(i + "   => Jumlah "+ orderFood.get(i));
			if(i.startsWith("Paket ")) {
				harga = getHargaMenu("paket",i);
				System.out.println("   harga satuan => Rp " + harga);
				total += harga * Integer.parseInt(orderFood.get(i));
			}else {
				harga = getHargaMenu("item",i);	
				System.out.println("   harga satuan => Rp " + harga);
				total += harga * Integer.parseInt(orderFood.get(i));
			}
		}
		System.out.println();
		System.out.println("-------------------------");
		int pajak = 10 * total /100;
		int totalSemua = pajak + total;
		double rupiah = (double)totalSemua;
		System.out.print("  Total Bayar + ppn(10%) => ");
		formatRupiah(rupiah);
		System.out.println("-------------------------");
		System.out.println();
		System.out.println();
		System.out.println("===============================");
		System.out.println("   Terimakasih Sudah Datang");
		System.out.println("===============================");
		input.close();
	}

	public void formatRupiah(double harga) {
		DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        System.out.printf("Harga Rupiah: %s %n", kursIndonesia.format(harga));
	}
	
	public void menuMain() {
		System.out.println("======   Pilih Menu Restaurant   ======");
		System.out.println();
		System.out.println("1. Tampil Menu Paket");
		System.out.println("2. Tampil Menu Per Item");
		System.out.println("3. Masukkan Pesanan");
		System.out.println("4. Lakukan Pembayaran");
		System.out.println("5. Keluar");
		System.out.print("Silahkan memilih sesuai nomor yang tertera: ");

		this.order = input.nextInt();
	}

	public void pilihMenu() {
		System.out.println();
		System.out.println("Pilih Menu");
		System.out.println("1. Paket");
		System.out.println("2. Item");
		System.out.print("Pilih menu paket atau item: ");
		int pilih = input.nextInt();
		switch (pilih) {
		case 1:
			System.out.println();
			daftarMenuPaket();
			break;
		case 2:
			System.out.println();
			daftarMenuItem();
			break;
		default:
			System.out.println();
			System.out.println("Pilihan Anda salah, silahkan memilih ulang");
			pilihMenu();
			break;

		}
		System.out.println();
	}

	public void daftarMenuPaket() {
		int count = 0;
		for (String i : menuPaket.keySet()) {
			System.out.println((count + 1) + ". " + i + ", Harga: " + menuPaket.get(i));
			System.out.print("Detail: ");
			tampilDetailPaket(i);
			count++;
		}
		System.out.println((count + 1) + ". Kembali ke menu utama");
		System.out.print("Silahkan memilih sesuai nomor yang tertera: ");
		int orderPaket = input.nextInt();
		System.out.print("Mau pesan berapa: ");
		int jmlhOrder = input.nextInt();
		String orderAdd;
		switch (orderPaket) {
		case 1:
			if(cekMenu("Paket A") > 0) {
				int tmbhOrder = cekMenu("Paket A") + jmlhOrder;
				orderanMenu("Paket A", Integer.toString(tmbhOrder));
			}else {
				orderanMenu("Paket A", Integer.toString(jmlhOrder));				
			}
			System.out.print("Apakah ingin menambah pesananan (Y/T): ");
			orderAdd = input.next();
			if (orderAdd.toLowerCase().equals("y")) {
				pilihMenu();
			} else {
				System.out.println();
				appMain();
			}
			break;
		case 2:
			if(cekMenu("Paket B") > 0) {
				int tmbhOrder = cekMenu("Paket A") + jmlhOrder;
				orderanMenu("Paket B", Integer.toString(tmbhOrder));
			}else {
				orderanMenu("Paket B", Integer.toString(jmlhOrder));				
			}
			System.out.print("Apakah ingin menambah pesananan (Y/T): ");
			orderAdd = input.next();
			if (orderAdd.toLowerCase().equals("y")) {
				pilihMenu();
			} else {
				System.out.println();
				appMain();
			}
			break;
		case 3:
			if(cekMenu("Paket C") > 0) {
				int tmbhOrder = cekMenu("Paket C") + jmlhOrder;
				orderanMenu("Paket C", Integer.toString(tmbhOrder));
			}else {
				orderanMenu("Paket C", Integer.toString(jmlhOrder));				
			}
			System.out.print("Apakah ingin menambah pesananan (Y/T): ");
			orderAdd = input.next();
			if (orderAdd.toLowerCase().equals("y")) {
				pilihMenu();
			} else {
				System.out.println();
				appMain();
			}
			break;
		case 4:
			System.out.println();
			appMain();
			break;
		default:
			System.out.println("Masukkan angka yang sesua pada menu");
			System.out.println("");
			daftarMenuPaket();
			break;
		}
	}

	public void daftarMenuItem() {
		int count = 0;
		for (String i : menuItem.keySet()) {
			System.out.println((count + 1) + ". Item " + i + ", Harga: " + menuItem.get(i));
			count++;
		}
		System.out.println((count + 1) + ". Kembali ke menu utama");
		System.out.print("Silahkan memilih sesuai nomor yang tertera: ");
		int orderPaket = input.nextInt();
		System.out.print("Mau pesan berapa: ");
		int jmlhOrder = input.nextInt();
		String orderAdd;
		switch (orderPaket) {
		case 1:
			if(cekMenu("Nasi Uduk") > 0) {
				int tmbhOrder = cekMenu("Nasi Uduk") + jmlhOrder;
				orderanMenu("Nasi Uduk", Integer.toString(tmbhOrder));
			}else {
				orderanMenu("Nasi Uduk", Integer.toString(jmlhOrder));				
			}
			System.out.print("Apakah ingin menambah pesananan (Y/T): ");
			orderAdd = input.next();
			if (orderAdd.toLowerCase().equals("y")) {
				pilihMenu();
			} else {
				System.out.println();
				appMain();
			}
			break;
		case 2:
			if(cekMenu("Ayam Goreng") > 0) {
				int tmbhOrder = cekMenu("Nasi Uduk") + jmlhOrder;
				orderanMenu("Ayam Goreng", Integer.toString(tmbhOrder));
			}else {
				orderanMenu("Ayam Goreng", Integer.toString(jmlhOrder));				
			}
			System.out.print("Apakah ingin menambah pesananan (Y/T): ");
			orderAdd = input.next();
			if (orderAdd.toLowerCase().equals("y")) {
				pilihMenu();
			} else {
				System.out.println();
				appMain();
			}
			break;
		case 3:
			if(cekMenu("Mie") > 0) {
				int tmbhOrder = cekMenu("Nasi Uduk") + jmlhOrder;
				orderanMenu("Mie", Integer.toString(tmbhOrder));
			}else {
				orderanMenu("Mie", Integer.toString(jmlhOrder));				
			}
			System.out.print("Apakah ingin menambah pesananan (Y/T): ");
			orderAdd = input.next();
			if (orderAdd.toLowerCase().equals("y")) {
				pilihMenu();
			} else {
				System.out.println();
				appMain();
			}
			break;
		case 4:
			if(cekMenu("Telur") > 0) {
				int tmbhOrder = cekMenu("Nasi Uduk") + jmlhOrder;
				orderanMenu("Telur", Integer.toString(tmbhOrder));
			}else {
				orderanMenu("Telur", Integer.toString(jmlhOrder));				
			}
			System.out.print("Apakah ingin menambah pesananan (Y/T): ");
			orderAdd = input.next();
			if (orderAdd.toLowerCase().equals("y")) {
				pilihMenu();
			} else {
				System.out.println();
				appMain();
			}
			break;
		case 5:
			if(cekMenu("Es Teh Manis") > 0) {
				int tmbhOrder = cekMenu("Nasi Uduk") + jmlhOrder;
				orderanMenu("Es Teh Manis", Integer.toString(tmbhOrder));
			}else {
				orderanMenu("Es Teh Manis", Integer.toString(jmlhOrder));				
			}
			System.out.print("Apakah ingin menambah pesananan (Y/T): ");
			orderAdd = input.next();
			if (orderAdd.toLowerCase().equals("y")) {
				pilihMenu();
			} else {
				System.out.println();
				appMain();
			}
			break;
		case 6:
			if(cekMenu("Air Putih") > 0) {
				int tmbhOrder = cekMenu("Air Putih") + jmlhOrder;
				orderanMenu("Air Putih", Integer.toString(tmbhOrder));
			}else {
				orderanMenu("Air Putih", Integer.toString(jmlhOrder));				
			}
			System.out.print("Apakah ingin menambah pesananan (Y/T): ");
			orderAdd = input.next();
			if (orderAdd.toLowerCase().equals("y")) {
				pilihMenu();
			} else {
				System.out.println();
				appMain();
			}
			break;
		case 7:
			System.out.println();
			appMain();
			break;
		default:
			System.out.println("Masukkan angka yang sesua pada menu");
			System.out.println("");
			daftarMenuItem();
			break;
		}
	}

	public void appMain() {
		menuMain();
		switch (this.order) {
		case 1:
			System.out.println();
			menuPaket();
			System.out.println();
			appMain();
			break;
		case 2:
			System.out.println();
			menuItem();
			System.out.println();
			appMain();
			break;
		case 3:
			pilihMenu();
			break;
		case 4:
			System.out.println();
			if(orderFood.size() > 0) {
				receipt();				
			}else {
				System.out.println("Belum terdapat pesanan, silahkan pesan dahulu!");
				System.out.println("");
				appMain();
			}
			break;
		case 5:
			System.out.println();
			System.out.println("Thankyou");
			break;
		default:
			System.out.println("Masukkan angka yang sesua pada menu");
			System.out.println("");
			appMain();
			break;
		}
	}

	
}
