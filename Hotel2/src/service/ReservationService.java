package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class ReservationService {

    private static ReservationService reservationService;

    public Collection<Reservation> reservations = new HashSet<>();
    public Collection<IRoom> rooms = new HashSet<>();

    public static ReservationService getInstance(){

        if(reservationService==null){
            reservationService = new ReservationService();
        }
        return reservationService;

    }

    public void addRoom(IRoom room){

        rooms.add(room);

    }


    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    //.......................................
    public IRoom getARoom(String roomId){
        for(IRoom room : rooms){
            if(room.getRoomNumber().equals(roomId)){
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> roomsOpenForReserve = new HashSet<>();
        if (reservations.isEmpty()) {
            return rooms;
        } else {
            for (Reservation reservation : reservations) {
                if (((!checkInDate.after(reservation.getCheckInDate())) && (!checkInDate.before(reservation.getCheckOutDate())))
                        || (((checkOutDate.after(reservation.getCheckInDate())) && (!checkInDate.before(reservation.getCheckOutDate()))))) {
                    for (IRoom room : rooms) {
                        if (!reservation.getRoom().equals(room)) {
                            roomsOpenForReserve.add(room);
                        }
                    }
                }
            }
        }
        return roomsOpenForReserve;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservation = new HashSet<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservation.add(reservation);
            }
        }
        return customerReservation;
    }

    public void printAllReseverations() {
        for (Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }





}
