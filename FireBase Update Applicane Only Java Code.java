TextView currentVersion = findViewById(R.id.updatedailog);
        currentVersion.setText("current Version Code:"+ getVersionCode());
        HashMap<String,Object> defaultsRate = new HashMap<>();
        defaultsRate.put("New Version Code",String.valueOf(getVersionCode()));
        remoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setFetchTimeoutInSeconds(10)
                .build();
        remoteConfig.setConfigSettingsAsync(configSettings);
        remoteConfig.setDefaultsAsync(defaultsRate);



        remoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()){
                    final  String new_version_code = remoteConfig.getString("new version code");
                    if (Integer.parseInt(new_version_code)> getVersionCode())
                        showDialog(new_version_code);
                }
            }
        });

    }
    private void showDialog(String VersionFromRemoteConfig) {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New Update Available")
                .setTitle("Old Version:1\nLatest Version:"+VersionFromRemoteConfig)
                .setPositiveButton("UPDATE",null)
                .show();
        dialog.setCancelable(false);

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=\"+\"com.sharepunjabishayari")));
                }catch (android.content.ActivityNotFoundException anfe){
                    startActivity(new Intent(Intent.ACTION_VIEW));
                    Uri.parse("https://www.shayariblog.xyz");
                }
            }
        });
    }

    public int getVersionCode() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("MYLOG", "NameNotFoundException:" + e.getMessage());
        }
        return packageInfo.versionCode;