package util;

import model.Client;
import model.CreditRequest;

import java.util.List;

public class CreditUtils {
    public static boolean approveCredit(Client client, List<CreditRequest> approvedRequests, CreditRequest request) {
        Double totalIncome = client.getSalary() - client.getPayments();
        if (approvedRequests.size() != 0) {
            for (CreditRequest approvedReq : approvedRequests) {
                if (approvedReq.getStatus().equals(Constants.STATUS_APPROVED)) {
                    totalIncome -= approvedReq.getMonthlyPayment();
                }
            }
        }

        Double mPayment = request.getMonthlyPayment();

        if (mPayment > totalIncome)
            return false;

        int percentage = (int) ((mPayment / totalIncome) * 100);
        return percentage <= 60;
    }
}
