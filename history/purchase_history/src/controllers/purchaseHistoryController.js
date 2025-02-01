const purchaseHistoryService = require('../services/purchaseHistoryService');

const getPurchaseHistory = async (req, res) => {
  try {
    const history = await purchaseHistoryService.getPurchaseHistory();
    res.json(history);
  } catch (error) {
    console.error('Error retrieving purchase history:', error);
    res.status(500).json({ message: 'Internal server error' });
  }
};

module.exports = {
  getPurchaseHistory,
};
