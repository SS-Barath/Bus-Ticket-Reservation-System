package BusResv;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Booking {
    String passengerName;
    int busNo;
    Date date;

    Booking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of passenger:");
        passengerName = scanner.next();
        System.out.println("Enter bus no:");
        busNo = scanner.nextInt();
        System.out.println("Enter date dd-mm-yyyy");
        String dateInput = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            date = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean isAvailable(ArrayList<Booking> bookings, ArrayList<Bus> buses, int busNo) {
        int capacity = 0;
        for (Bus bus : buses) {
            if (bus.getBusNo() == busNo) {
                capacity = bus.getCapacity();
                break; // Exit the loop once the capacity is found
            }
        }
        int booked = 0;

        for (Booking b : bookings) {
            if (b.busNo == busNo && b.date.equals(date)) {
                booked++;
            }
        }

        // Example logic for availability check
        return booked < capacity;
    }

    public static void main(String[] args) {
        Booking booking = new Booking();
        ArrayList<Booking> bookings = new ArrayList<>();
        ArrayList<Bus> buses = new ArrayList<>();

        // Populate 'buses' with Bus instances

        if (booking.isAvailable(bookings, buses, 123)) {
            bookings.add(booking);
            System.out.println("Your booking is confirmed");
        } else {
            System.out.println("Sorry. Bus is Full. Try another bus or date.");
        }
    }
}
