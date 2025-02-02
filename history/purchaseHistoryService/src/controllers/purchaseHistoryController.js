const PurchaseHistoryService = require('../services/purchaseHistoryService');

class PurchaseHistoryController {
  static async getPurchaseHistory(req, res) {
    try {
      const history = await PurchaseHistoryService.getAll();
      res.json(history);
    } catch (error) {
      console.error('Error retrieving purchase history:', error);
      res.status(500).json({ message: 'Internal server error' });
    }
  }
}

module.exports = PurchaseHistoryController;
