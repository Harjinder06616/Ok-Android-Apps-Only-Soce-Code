//  Banner Ads Start

MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//      Banner Ads End
//         Interstitial Ads Time Loader Start
        prepaperAD();
        ScheduledExecutorService scheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG", "Interstital not Loaded");
                        }
                        prepaperAD();
                    }
                });
            }
        }, 60, 60, TimeUnit.SECONDS);
		
//		      Interstitial Ads Time Loader End

//        Interstitial Ads Banner Start  
		  }
    public void prepaperAD() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

//       Interstitial Ads Banner End

//		  No Internet Connected All Vireyables Start ( Problom Is This Is On Crete Vireyables )

		 if (!isconnected(MainActivity.this)) buidDialog(MainActivity.this).show();
        userList = new ArrayList<>();
        rvList = findViewById(R.id.reshow);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(this));
		
//       No Internet Connected All Vireyables Start ( Problom Is This Is On Crete Vireyables )


		
		
		
		
		
		