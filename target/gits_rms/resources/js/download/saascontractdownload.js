function saasContractedDownloadWindow(saasFileName,fileSize,fileType)
			{
	     		var url = 'downloadSaasContract.action?saasFileName='+saasFileName+'&fileSize='+fileSize+'&fileType='+fileType;
		    	window.open(url,'jav','top=100,left=100,width=800,height=500,resizable=1,scrollbars=1');
	     	}