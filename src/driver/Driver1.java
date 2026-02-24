import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Driver1 {
    
    static class OrderItem {
        String nama;
        int porsi;
        int harga;
        int total;

        public OrderItem(String nama, int porsi, int harga, int total) {
            this.nama = nama;
            this.porsi = porsi;
            this.harga = harga;
            this.total = total;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Map<String, String[]> menuMap = new LinkedHashMap<>();
        menuMap.put("NGS", new String[]{"Nasi Goreng Spesial", "15000"});
        menuMap.put("AP", new String[]{"Ayam Penyet", "20000"});
        menuMap.put("SA", new String[]{"Sate Ayam (10 Tusuk)", "25000"});
        menuMap.put("BU", new String[]{"Bakso Urat", "18000"});
        menuMap.put("MAP", new String[]{"Mie Ayam Pangsit", "15000"});
        menuMap.put("GG", new String[]{"Gado-Gado", "15000"});
        menuMap.put("SAM", new String[]{"Soto Ayam", "17000"});
        menuMap.put("RD", new String[]{"Rendang Daging", "25000"});
        menuMap.put("IB", new String[]{"Ikan Bakar", "35000"});
        menuMap.put("NUK", new String[]{"Nasi Uduk Komplit", "20000"});

        List<OrderItem> orders = new ArrayList<>();
        int subtotal = 0;

        while (scanner.hasNext()) {
            String input = scanner.next();
            
            if (input.equalsIgnoreCase("END")) {
                break;
            }
            
            String kodeMenu = input;
            int porsiButet = scanner.nextInt();
            
            int porsiUcok = porsiButet * 2;
            int totalPorsi = porsiButet + porsiUcok;
            
            if (menuMap.containsKey(kodeMenu)) {
                String namaMenu = menuMap.get(kodeMenu)[0];
                int hargaSatuan = Integer.parseInt(menuMap.get(kodeMenu)[1]);
                int totalHarga = totalPorsi * hargaSatuan;
                
                orders.add(new OrderItem(namaMenu, totalPorsi, hargaSatuan, totalHarga));
                subtotal += totalHarga;
            }
        }

        int diskonPersen = 0;
        if (subtotal >= 500000) {
            diskonPersen = 25;      
        } else if (subtotal >= 400000) {
            diskonPersen = 20;      
        } else if (subtotal >= 300000) {
            diskonPersen = 15;      
        } else if (subtotal >= 200000) {
            diskonPersen = 10;      
        } else if (subtotal >= 100000) {
            diskonPersen = 5;       
        }

        int potonganHarga = (subtotal * diskonPersen) / 100;
        int totalPembayaran = subtotal - potonganHarga;

        System.out.printf("%-24s %-8s %-10s %-10s\n", "Menu", "Porsi", "Harga", "Total");
        System.out.println("==========================================================");
        
        for (OrderItem item : orders) {
            System.out.printf("%-24s %-8d %-10d %-10d\n", item.nama, item.porsi, item.harga, item.total);
        }
        
        System.out.println("==========================================================");
        System.out.printf("%-44s %d\n", "Total Pembayaran", totalPembayaran);
        
        scanner.close();
    }
}
