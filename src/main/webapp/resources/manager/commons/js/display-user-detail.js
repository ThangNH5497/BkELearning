
function initUserDetail(user) {
	try {
		if (user != {}&&user!=undefined&&user!=""&&user!=null&&user!=[]) {
			// load anh
			$('#user-detail-img').removeClass('hidden');
			$('#user-detail-img').attr('src',
					rootLocation + "resources/commons/image/default-user.jpg");
			$('#user-detail-img').attr("src", rootLocation + user.image);
			obj.initData('user-detail', 'user-detail-row', [ user ]);
		} else {
			$('#user-detail-img').removeAttr("src");
			$('#user-detail-img').addClass('hidden');

			obj.initData('user-detail', 'user-detail-row', []);
		}
	} catch (e) {
		// TODO: handle exception
	}

}
